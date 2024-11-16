package tech.ishe.smatechpos.views.receipts

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import tech.ishe.smatechpos.data.models.NetworkResponse
import tech.ishe.smatechpos.data.models.OrderModel
import tech.ishe.smatechpos.viewmodels.LoadingViewModel
import tech.ishe.smatechpos.viewmodels.OrdersViewModel
import tech.ishe.smatechpos.views.receipts.widgets.ReceiptCard
import tech.ishe.smatechpos.views.utils.ScreenDimensions

@Composable
fun ReceiptsTab(navController: NavController, modifier: Modifier) {
    val context = LocalContext.current
    val ordersViewModel: OrdersViewModel =
        ViewModelProvider(context as ComponentActivity)[OrdersViewModel::class.java]

    LaunchedEffect(Unit) {
        ordersViewModel.getOrders()
    }

    val ordersResult = ordersViewModel.ordersResult.observeAsState()

    var receiptsList: List<OrderModel>

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 2.dp, horizontal = 4.dp),
    ) {

        when (val result = ordersResult.value) {
            is NetworkResponse.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = result.message,
                        color = Color.Red
                    )
                }
            }

            NetworkResponse.Loading -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            }

            is NetworkResponse.Success -> {
                receiptsList = result.data
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (receiptsList.isEmpty()) {
                        item {
                            Box(modifier = Modifier
                                .height((ScreenDimensions.screenHeightDp * 0.8).dp)
                                .fillParentMaxWidth(),
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = "No order receipts at the moment...")
                            }
                        }
                    } else {
                        items(receiptsList.size) { index ->
                            ReceiptCard(receiptsList[index], index, navController)
                        }
                    }
                }
            }

            null -> {}
        }

    }
}