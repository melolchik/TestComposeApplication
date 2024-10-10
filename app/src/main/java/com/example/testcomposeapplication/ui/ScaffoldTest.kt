package com.example.testcomposeapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TestScaffold() {
    ModalNavigationDrawer(drawerContent = {
        Column {
            Text(text = "Text1")
            Text(text = "Text2")
            Text(text = "Text3")
        }
    },
        content = {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = "Top App Bar")
                },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = null
                            )
                        }
                    })
            },
            bottomBar = {
                BottomAppBar() {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }
            }

        ) {
            Text(
                modifier = Modifier.padding(it),
                text = "Text"
            )
        }
    })
}