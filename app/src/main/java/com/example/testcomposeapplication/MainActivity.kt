package com.example.testcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.testcomposeapplication.ui.InsatgramProfileCard
import com.example.testcomposeapplication.ui.MainScreen
import com.example.testcomposeapplication.ui.MainViewModel
import com.example.testcomposeapplication.ui.theme.TestComposeApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModelVk by viewModels<MainViewModelVk>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            //TestInstagram(viewModel)
            TestVk(viewModelVk = viewModelVk)

        }
    }
}

@Composable
private fun TestVk(viewModelVk: MainViewModelVk){
    TestComposeApplicationTheme {
        MainScreen(viewModel = viewModelVk)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TestInstagram(viewModel: MainViewModel) {
    TestComposeApplicationTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            //val lazyListState = rememberLazyListState()
            val modles = viewModel.models.observeAsState(listOf())
            val skope = rememberCoroutineScope()
            // LazyVerticalGrid( columns = GridCells.Fixed(2)) {
            LazyColumn() {

                    items(modles.value, key = {it.id}){
                        model ->
                        val dismissState = rememberSwipeToDismissBoxState()
                        if(dismissState.currentValue == SwipeToDismissBoxValue.EndToStart){
                            viewModel.deleteItem(model)
                        }
                        SwipeToDismissBox(state = dismissState,
                            enableDismissFromEndToStart = true,
                            enableDismissFromStartToEnd = false,
                            backgroundContent = {
                            Box(
                                modifier = Modifier
                                    .background(Color.Red.copy(alpha = 0.5f))
                                    .padding(16.dp)
                                    .fillMaxSize()
                            ){
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = "DeleteItem",
                                    color = Color.White,
                                    fontSize = 24.sp)
                            }
                        }) {
                            InsatgramProfileCard(model = model,
                                onFollowedButtonClickListener = {
                                    viewModel.changeFollowedStatus(model)
                                })
                        }
                    }
            }

        }
    }
}



