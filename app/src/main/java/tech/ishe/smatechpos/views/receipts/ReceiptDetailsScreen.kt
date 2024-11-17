package tech.ishe.smatechpos.views.receipts

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Toast
import android.graphics.Color as Colour
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.PictureAsPdf
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tech.ishe.smatechpos.R
import tech.ishe.smatechpos.data.models.OrderModel
import tech.ishe.smatechpos.views.utils.theme.OrangeEnd
import tech.ishe.smatechpos.views.utils.theme.OrangeStart
import tech.ishe.smatechpos.views.receipts.widgets.OrderedItemCard
import tech.ishe.smatechpos.views.utils.ScreenDimensions
import tech.ishe.smatechpos.views.utils.getGradient
import tech.ishe.smatechpos.views.utils.theme.GreenEnd
import tech.ishe.smatechpos.views.utils.theme.GreenStart
import java.io.File
import java.io.FileOutputStream
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptDetailsScreen(orderModel: OrderModel, navController: NavController) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Receipt Details",
                    modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                ) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    },
                        ) {
                       Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = "back")
                    }
                }
                )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((ScreenDimensions.screenHeightDp * 0.15).dp)
                    .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                    .background(MaterialTheme.colorScheme.onBackground)
            ){
                Box(
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                        .background(
                            brush = getGradient(GreenStart, GreenEnd),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Button(
                        onClick = {
                            saveReceiptAsPdf(context, orderModel)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row {
                            Icon(imageVector = Icons.Rounded.PictureAsPdf, contentDescription = "add to cart")
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Save To PDF")
                        }
                    }
                }
            }
        }

    ) {innerPadding->
        DetailsView(orderModel, Modifier.padding(innerPadding))
    }
}

@Composable
fun DetailsView(orderModel: OrderModel, modifier: Modifier) {
    Box(
        modifier= modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height((ScreenDimensions.screenHeightDp * 0.7).dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)

    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .height((ScreenDimensions.screenHeightDp * 0.25).dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Order ref:")
                        Text(
                            text = orderModel.orderCode,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Ordered items:")
                        Text(
                            text = "${orderModel.orderedItems.size}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val formattedDeliveryFee =
                            String.format(Locale.UK, "%.2f", orderModel.deliverFee)
                        Text(text = "Delivery Fee:")
                        Text(
                            text = "$${formattedDeliveryFee}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val formattedSubtotal =
                            String.format(Locale.UK, "%.2f", orderModel.subTotal)
                        Text(text = "Subtotal:")
                        Text(
                            text = "$${formattedSubtotal}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val formattedTotal = String.format(Locale.UK, "%.2f", orderModel.total)
                        Text(text = "Order total:")
                        Text(
                            text = "$${formattedTotal}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }


            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .background(OrangeStart)
                    .height(1.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ordered products",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(

            ) {
                val orderItems = orderModel.orderedItems
                items(orderItems.size) { index ->
                    OrderedItemCard(orderItems[index])
                }
            }
        }
    }

}

fun saveReceiptAsPdf(context: Context, orderModel: OrderModel) {
    val pdfDocument = PdfDocument()

    val pageWidth = 1080
    val pageHeight = 1920
    val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

    val page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas

    val titlePaint = Paint().apply {
        color = Colour.BLACK
        textSize = 36f
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }
    val contentPaint = Paint().apply {
        color = Colour.BLACK
        textSize = 28f
    }
    val linePaint = Paint().apply {
        color = Colour.LTGRAY
        strokeWidth = 2f
    }

    val image1 = BitmapFactory.decodeResource(context.resources, R.drawable.original)

    val imageHeight = 300
    val imageWidth = 300

    val imageY = 20f
    canvas.drawBitmap(Bitmap.createScaledBitmap(image1, imageWidth, imageHeight, false), 40f, imageY, null)

    var startY = imageY + imageHeight + 40f

    // Draw title
    canvas.drawText("Order Details", 40f, startY, titlePaint)
    startY += 60f

    fun drawRow(label: String, value: String) {
        canvas.drawText(label, 40f, startY, contentPaint)
        canvas.drawText(value, pageWidth - 600f, startY, contentPaint)
        startY += 40f
    }

    drawRow("Order Ref:", orderModel.orderCode)
    drawRow("Ordered Items:", orderModel.orderedItems.size.toString())
    drawRow("Delivery Fee:", "$${String.format(Locale.UK, "%.2f", orderModel.deliverFee)}")
    drawRow("Subtotal:", "$${String.format(Locale.UK, "%.2f", orderModel.subTotal)}")
    drawRow("Order Total:", "$${String.format(Locale.UK, "%.2f", orderModel.total)}")

    startY += 20f
    canvas.drawLine(40f, startY, pageWidth - 40f, startY, linePaint)
    startY += 40f

    canvas.drawText("Ordered Products", 40f, startY, titlePaint)
    startY += 60f

    for (item in orderModel.orderedItems) {
        canvas.drawText(item.productName, 40f, startY, contentPaint)
        canvas.drawText("Price: $${item.price}", pageWidth - 500f, startY, contentPaint)
        canvas.drawText("Qty: ${item.quantity}", pageWidth - 200f, startY, contentPaint)
        startY += 40f
    }

    pdfDocument.finishPage(page)

    val outputDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val outputFile = File(outputDir, "receipt_${orderModel.orderCode.substring(0, 8)}.pdf")
    FileOutputStream(outputFile).use { fos ->
        pdfDocument.writeTo(fos)
    }

    pdfDocument.close()

    Toast.makeText(context, "PDF saved to: ${outputFile.absolutePath}", Toast.LENGTH_SHORT).show()
}


//fun saveReceiptAsPdf(context: Context, orderModel: OrderModel) {
//    val pdfDocument = PdfDocument()
//
//    val pageWidth = 1080
//    val pageHeight = 1920
//    val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
//
//    val page = pdfDocument.startPage(pageInfo)
//    val canvas = page.canvas
//
//    val titlePaint = Paint().apply {
//        color = Colour.BLACK
//        textSize = 36f
//        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
//    }
//    val contentPaint = Paint().apply {
//        color = Colour.BLACK
//        textSize = 28f
//    }
//    val linePaint = Paint().apply {
//        color = Colour.LTGRAY
//        strokeWidth = 2f
//    }
//
//    val startX = 40f
//    var startY = 100f
//    canvas.drawText("Order Details", startX, startY, titlePaint)
//    startY += 60f
//
//    fun drawRow(label: String, value: String) {
//        canvas.drawText(label, startX, startY, contentPaint)
//        canvas.drawText(value, pageWidth - 600f, startY, contentPaint)
//        startY += 40f
//    }
//
//    drawRow("Order Ref:", orderModel.orderCode)
//    drawRow("Ordered Items:", orderModel.orderedItems.size.toString())
//    drawRow("Delivery Fee:", "$${String.format(Locale.UK, "%.2f", orderModel.deliverFee)}")
//    drawRow("Subtotal:", "$${String.format(Locale.UK, "%.2f", orderModel.subTotal)}")
//    drawRow("Order Total:", "$${String.format(Locale.UK, "%.2f", orderModel.total)}")
//
//    startY += 20f
//    canvas.drawLine(startX, startY, pageWidth - 40f, startY, linePaint)
//    startY += 40f
//
//    canvas.drawText("Ordered Products", startX, startY, titlePaint)
//    startY += 60f
//
//    for (item in orderModel.orderedItems) {
//        canvas.drawText(item.productName, startX, startY, contentPaint)
//        canvas.drawText("Price: $${item.price}", pageWidth - 500f, startY, contentPaint)
//        canvas.drawText("Qty: ${item.quantity}", pageWidth - 200f, startY, contentPaint)
//        startY += 40f
//    }
//
//    pdfDocument.finishPage(page)
//
//    val outputDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//    val outputFile = File(outputDir, "receipt_${orderModel.orderCode.substring(0,8)}.pdf")
//    FileOutputStream(outputFile).use { fos ->
//        pdfDocument.writeTo(fos)
//    }
//
//    pdfDocument.close()
//
//    Toast.makeText(context, "PDF saved to: ${outputFile.absolutePath}", Toast.LENGTH_SHORT).show()
//}




