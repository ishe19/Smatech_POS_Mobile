package tech.ishe.smatechpos.views.receipts

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import tech.ishe.smatechpos.data.models.OrderModel
import tech.ishe.smatechpos.viewmodels.LoadingViewModel
import tech.ishe.smatechpos.viewmodels.OrdersViewModel
import tech.ishe.smatechpos.views.receipts.widgets.ReceiptCard

@Preview(showBackground = true)
@Composable
fun ReceiptsTab() {
    val context = LocalContext.current
    val ordersViewModel: OrdersViewModel = ViewModelProvider(context as ComponentActivity)[OrdersViewModel::class.java]

    val loadingViewModel: LoadingViewModel =
        ViewModelProvider(context)[LoadingViewModel::class.java]
//    LaunchedEffect {
//
//    }

    var receiptsList: List<OrderModel> = emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 4.dp),
    ) {
        loadingViewModel.isLoading(true)
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            if(receiptsList.isEmpty()){
                item {
                    Text(text = "No order receipts at the moment...")
                }
            } else {
                items(receiptsList.size){ index ->
                    ReceiptCard(receiptsList[index])
                }
            }
        }

    }
}