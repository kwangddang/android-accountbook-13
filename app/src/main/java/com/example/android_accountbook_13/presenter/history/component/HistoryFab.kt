package com.example.android_accountbook_13.presenter.history.component

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_accountbook_13.R
import com.example.android_accountbook_13.ui.theme.AccountBookTheme
import com.example.android_accountbook_13.ui.theme.Yellow

@Composable
fun HistoryFab(
    onClick: () -> Unit
) {
    AccountBookTheme() {
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = Yellow,
            contentColor = MaterialTheme.colors.onSecondary
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus), 
                contentDescription = stringResource(id = R.string.fab))
        }
    }
}

@Preview
@Composable
private fun FabPreview() {
    HistoryFab(){}
}