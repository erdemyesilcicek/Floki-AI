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
import com.erdemyesilcicek.flokiai.viewmodels.GeminiViewModel
import com.erdemyesilcicek.flokiai.viewmodels.LoadingViewModel

@Composable
fun LoadingPage(loadingViewModel: LoadingViewModel, geminiViewModel: GeminiViewModel) {
    val genre = loadingViewModel.genre
    val season = loadingViewModel.season
    val animals = loadingViewModel.animals
    val characters = loadingViewModel.characters
    val family = loadingViewModel.family
    val userInformation = loadingViewModel.userInformation

    val prompt = "Write a story for children that is educational, entertaining, and based on the following parameters and instructions. Each parameter should have a unique role in the story, and the narration should be simple, fluent, and engaging to capture children's attention.\n" +
            "\n" +
            "Parameters:\n" +
            "Genre: ${genre}\n" +
            "Season: ${season}\n" +
            "Animals: ${animals}\n" +
            "Characters: ${characters}\n" +
            "Family: ${family}\n" +
            "Main Character Information:\n" +
            "Name: ${userInformation?.yourName}\n" +
            "Gender: ${userInformation?.gender}\n" +
            "Age: ${userInformation?.age}\n" +
            "Mom Name: ${userInformation?.momName}\n" +
            "Dad Name: ${userInformation?.dadName}\n" +
            "Sibling Name: ${userInformation?.siblingName}\n" +
            "Pet Name: ${userInformation?.petName}\n" +
            "Language: Turkish\n" +
            "Story Categories:\n" +
            "\n" +
            "Type: Specified type (Adventure, Fantastic, Mystery, Space, Trip, Family).\n" +
            "Season: Specified season (Summer, Spring, Autumn, Winter).\n" +
            "Animals: Up to 3 animal selections (Bear, Boar, Camel, Cat, Chicken, Cow, Crocodile, Dog, Elephant, Fox, Lion, Monkey, Seal, Wolf).\n" +
            "Characters: Up to 3 character selections (Chef, Clown, Cowboy, Dwarf, Elf, King, Ninja, Pirate, Thief, Wizard).\n" +
            "Family: Up to 3 family members (Dad, Mom, Sibling, Pet).\n" +
            "Main Character:\n" +
            "The name, age, gender, and family members of the main character must be specified. The story should revolve around this character, narrated from their perspective or based on an event they experience. However, the character's name should not be repeated excessively; the narration must use variety.\n" +
            "\n" +
            "Language Preference:\n" +
            "The story must be written in the specified language. If no language is chosen, English is the default.\n" +
            "\n" +
            "Instructions:\n" +
            "Flow:\n" +
            "The story must have a fluent narrative, avoiding repetition, with events progressing logically. Each event should transition naturally to the next.\n" +
            "\n" +
            "Animals and Other Characters:\n" +
            "The chosen animals and characters must have independent roles in the story, each uniquely contributing. For example, one animal might face a problem while another provides a solution. Interactions with animals or characters should not repeatedly use phrases like “encounter”; there must be variety in narration.\n" +
            "\n" +
            "Educational and Entertaining Content:\n" +
            "The story should convey a meaningful lesson or encourage imagination in children. The message should be subtle and naturally embedded through events and characters.\n" +
            "\n" +
            "Length:\n" +
            "The story should be medium-long, with a full and satisfying plot progression from start to finish.\n" +
            "\n" +
            "Narrative Style:\n" +
            "The language should be simple and easily understandable for children.\n" +
            "Descriptions should be vivid yet straightforward.\n" +
            "The story's world should be colorful, lively, and stimulate the imagination.\n" +
            "Story Format:\n" +
            "Story Title\n" +
            "The Story Itself\n" +
            "A one-sentence summary\n" +
            "\n" +
            "Special Instructions for AI:\n" +
            "The name, age, gender, and family details of the main character should form the foundation of the story. However, avoid excessive repetition of names by using pronouns or descriptions to add variety.\n" +
            "Each animal and character should serve a purpose in the story. For example, one animal might bring wisdom or a solution, while another creates a funny situation. Characters should play roles that support the main character's journey or adventure.\n" +
            "The story must adhere to the specified parameters and avoid unnecessary details. However, transitions between events must be logical, avoiding abrupt jumps.\n" +
            "Dialogues that capture children's attention can be included. They should be short and reflect the personality of the characters.\n" +
            "At the end of the story, include a message or lesson for children naturally through the events. This message might focus on themes like \"friendship,\" \"courage,\" or \"love for nature.\"\n" +
            "Any problem-solving should involve the main character's efforts and interactions with animals or characters, emphasizing the importance of collaboration and perseverance."
    geminiViewModel.getGeminiData(prompt)

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

/*
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
            "Evcil hayvan Adı: ${userInformation?.petName}\n" +
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


     */