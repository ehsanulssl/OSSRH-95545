package com.ssl.plugin_development

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ssl.plugin_development.ui.theme.MyApplicationTheme
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current

            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(Modifier, contentAlignment = Alignment.Center) {
                        Button(onClick = {
                            val sslCommerzInitialization = SSLCommerzInitialization(
                                "testbox",
                                "qwerty",
                                100.0,
                                SSLCCurrencyType.BDT,
                                "123456789098765",
                                "yourProductType",
                                SSLCSdkType.TESTBOX
                            )
                            IntegrateSSLCommerz
                                .getInstance(context)
                                .addSSLCommerzInitialization(sslCommerzInitialization)
                                .buildApiCall(object : SSLCTransactionResponseListener{
                                    override fun transactionSuccess(p0: SSLCTransactionInfoModel?) {}
                                    override fun transactionFail(p0: String?) {}
                                    override fun closed(p0: String?) {}
                                })
                        }){
                            Text(
                                text = "Pay now",
                            )
                        }
                    }
                }
            }
        }
    }
}