package com.erdemyesilcicek.flokiai.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.animations.LottieAnimation
import com.erdemyesilcicek.flokiai.utils.myFont
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel

@Composable
fun LoadingPage(loadingViewModel: LoadingViewModel) {
    val genre = loadingViewModel.genre
    val season = loadingViewModel.season
    val animals = loadingViewModel.animals
    val characters = loadingViewModel.characters
    val family = loadingViewModel.family
    val userInformation = loadingViewModel.userInformation

    val prompt = "Bir masal yazmanı istiyorum.\n" +
            "Masal, çocuklar için uygun, eğitici ve eğlenceli olmalı. Masalın konusu, aşağıdaki parametrelerde verilen seçimlere dayalı olacak. Lütfen her parametreyi masala dahil et ve anlatımı, çocukların ilgisini çekecek şekilde sade ve yaratıcı yap.\n" +
            "\n" +
            "Parametreler:\n" +
            "Tür: ${genre}\n" +
            "Mevsim: ${season}\n" +
            "Hayvanlar: ${animals}\n" +
            "Karakterler: ${characters}\n" +
            "Aile Bireyleri: ${family}\n" +
            "Ana Karakter Bilgileri:\n" +
            "İsim: ${userInformation?.yourName}\n" +
            "Cinsiyet: ${userInformation?.gender}\n" +
            "Yaş: ${userInformation?.age}\n" +
            "Anne Adı: ${userInformation?.momName}\n" +
            "Baba Adı: ${userInformation?.dadName}\n" +
            "Kardeş Adı: ${userInformation?.siblingName}\n" +
            "Dil Tercihi: Türkçe\n" +
            "Özel Talimatlar:\n" +
            "Ana Karakter:\n" +
            "Verilen isim, yaş, cinsiyet, anne adı, baba adı ve kardeş adı bir ana karaktere ait olmalı. Masal, bu ana karakterin etrafında şekillenmeli ve olaylar onun bakış açısıyla veya onun yaşadığı bir hikaye etrafında ilerlemeli.\n" +
            "Hayvanlar ve Diğer Karakterler:\n" +
            "Her hayvan ve karakter masalda ayrı bir şekilde yer almalı ve birbirleriyle eşleştirilmemeli. Her biri kendi benzersiz hikaye rolüne sahip olmalı.\n" +
            "Eğitici ve Eğlenceli:\n" +
            "Hikaye, çocuklara bir ders verebilmeli veya onların hayal gücünü geliştirici bir mesaj içermeli.\n" +
            "Uzunluk:\n" +
            "Masalın uzunluğu, kısa-orta düzeyde olmalı. Çok uzun olmayacak şekilde özet, ancak hikayeyi tamamlayacak kadar detaylı olmalı.\n" +
            "Anlatım Tarzı:\n" +
            "Anlatım dili, çocukların kolayca anlayabileceği kadar sade ve net olmalı. Masaldaki olaylar akıcı ve dikkat çekici bir şekilde anlatılmalı.\n" +
            "Dil Tercihi:\n" +
            "Yazılan masal, Dil Tercihi parametresinde belirtilen dilde olmalı. Eğer belirtilen dil seçilmezse, varsayılan olarak İngilizce yazılmalı.\n" +
            "Lütfen yukarıdaki tüm talimatları takip ederek bir masal oluştur. Bana bunu gönderdiğim parametreleri de ekleyerek, JSON formatında geri döndür."

    println(prompt)

    //generateTale(prompt)
    //println("LoadingPage: genre: $genre, season: $season, animals: $animals, characters: $characters, family: $family, userInformation: $userInformation")

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LottieAnimation(animation = R.raw.bigstick)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "PLEASE WAIT!",
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = myFont,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "We are loading your data...",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = myFont,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}