package com.example.testcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testcomposeapplication.ui.InsatgramProfileCard
import com.example.testcomposeapplication.ui.MainScreen
import com.example.testcomposeapplication.ui.MainViewModel
import com.example.testcomposeapplication.ui.PostCard
import com.example.testcomposeapplication.ui.TestScaffold
import com.example.testcomposeapplication.ui.theme.TestComposeApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModelVk by viewModels<MainViewModelVk>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            Test(viewModel)
//            TestComposeApplicationTheme {
//                InsatgramProfileCard(viewModel)
//                //MainScreen(viewModelVk)
//            }

        }
    }
}

@Composable
private fun Test(viewModel: MainViewModel) {
    TestComposeApplicationTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            val scroll = rememberScrollState()
            val modles = viewModel.models.observeAsState(listOf())
           // LazyColumn {
            LazyVerticalGrid( columns = GridCells.Fixed(2)) {

                    items(modles.value){
                        model ->
                        InsatgramProfileCard(model = model,
                            onFollowedButtonClickListener = {
                                viewModel.changeFollowedStatus(model)
                            })
                    }

            }
        }
    }
}



