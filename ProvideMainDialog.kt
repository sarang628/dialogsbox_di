package com.sarang.torang.di.dialogsbox_di

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.torang.FeedDialogsViewModel
import com.sarang.torang.MainDialogs
import com.sarang.torang.RootNavController
import com.sarang.torang.compose.type.LocalCommentBottomSheet
import com.sarang.torang.compose.type.LocalMenuBottomSheet
import com.sarang.torang.compose.type.LocalReportBottomSheetType
import com.sarang.torang.compose.type.LocalRestaurantBottomSheet
import com.sarang.torang.compose.type.LocalShareBottomSheet
import com.sarang.torang.di.bottomsheet_di.provideFeedMenuBottomSheetDialog
import com.sarang.torang.di.main_di.CommentBottomDialogSheetData
import com.sarang.torang.di.main_di.provideCommentBottomDialogSheet
import com.sarang.torang.di.report_di.provideReportModal
import com.sarang.torang.di.report_di.provideShareBottomSheetDialog

@Composable
fun ProvideMainDialog(
    dialogsViewModel        : FeedDialogsViewModel = hiltViewModel(),
    tag                     : String               = "__ProvideMainDialog",
    rootNavController       : RootNavController,
    restaurantBottomSheet   : @Composable ( @Composable () -> Unit ) -> Unit     = { },
    content                 : @Composable (PaddingValues) -> Unit = { Log.i(tag, "content does not set")  }
) {
    val tag = "__ProvideMainDialog"
    val uiState by dialogsViewModel.uiState.collectAsState()
    CompositionLocalProvider(
        LocalCommentBottomSheet provides { reviewId ->
            provideCommentBottomDialogSheet(rootNavController).invoke(
                CommentBottomDialogSheetData(
                    reviewId = reviewId,
                    onHidden = { dialogsViewModel.closeComment() },
                    content = content
                )
            )
        },
        LocalMenuBottomSheet provides provideFeedMenuBottomSheetDialog(),
        LocalShareBottomSheet provides provideShareBottomSheetDialog(uiState.showShare),
        LocalRestaurantBottomSheet provides restaurantBottomSheet,
        LocalReportBottomSheetType provides provideReportModal()
    ) {
        MainDialogs(
            uiState = uiState,
            onEdit = rootNavController.modReview(),
        )
    }
}
