package com.erdemyesilcicek.flokiai.constants

import com.erdemyesilcicek.flokiai.datas.UserInformationModel

fun GeminiAiTalePrompt(
    genre: String,
    season: String,
    animals: List<String>,
    characters: List<String>,
    family: List<String>,
    userInformation: UserInformationModel?,

    ): String {
    val dadName = userInformation?.dadName
    val momName = userInformation?.momName
    val siblingName = userInformation?.siblingName
    val petName = userInformation?.petName
    val petSpecies = userInformation?.petSpecies

    fun getDadInfo(): String {
        return if (family.contains("Dad")) {
            "Dad ($dadName)"
        } else {
            ""
        }
    }

    fun getMomInfo(): String {
        return if (family.contains("Mom")) {
            "Mom ($momName)"
        } else {
            ""
        }
    }
    fun getSiblingInfo(): String {
        return if (family.contains("Sibling")) {
            "Sibling ($siblingName)"
        } else {
            ""
        }
    }
    fun getPetInfo(): String {
        return if (family.contains("Pet")) {
            "Pet ($petName)"
        } else {
            ""
        }
    }

    return "Write a medium-long ${userInformation?.language} children's ${genre} story that is both educational and entertaining. The story should take place in the ${season} and feature the following animals: ${animals}. It should also include the characters: ${characters}. The family members involved in the story are ${getDadInfo()} ${getMomInfo()} ${getSiblingInfo()} ${getPetInfo()}. whose species is (${userInformation?.petSpecies}). The main character is ${userInformation?.yourName}, an ${userInformation?.age}-year-old ${userInformation?.gender}. Make sure each animal and character plays a unique and meaningful role in the narrative. Use pronouns or varied expressions to avoid repetitive naming. Ensure the story progresses logically and smoothly, and include short, personality-driven dialogues to enhance engagement and bring the characters to life. The story should subtly convey an important value such as friendship, courage, or honesty, without being overly didactic. Return the result in JSON format with the following fields: TaleTitle (the title of the story), TaleItself (the complete story written in ${userInformation?.language}), TaleSummary (a one-sentence summary that encapsulates the central moral or message of the tale), and EstimatedReadTimeMinutes (an estimated average reading time in minutes). Make sure the story is enjoyable for children, while also carrying an educational value."
    /*
    return "Write a story for children that is educational, entertaining, and based on the following parameters and instructions. Each parameter should have a unique role in the story, and the narration should be simple, fluent, and engaging to capture children's attention.\n" +
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
            "Language: ${userInformation?.language}\n" +
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
            "Summary:\n"+
            "The tale summary should consist of one sentence and should not contain repetitive text.\n"+
            "Story Format (Even if the application's language changes, the titles in the Tale Format section must remain fixed.) The titles are as follows:\n" +
            "\n" +
            "TaleTitle\n" +
            "TaleItself\n" +
            "TaleSummary\n" +
            "\n" +
            "Special Instructions for AI:\n" +
            "The name, age, gender, and family details of the main character should form the foundation of the story. However, avoid excessive repetition of names by using pronouns or descriptions to add variety.\n" +
            "Each animal and character should serve a purpose in the story. For example, one animal might bring wisdom or a solution, while another creates a funny situation. Characters should play roles that support the main character's journey or adventure.\n" +
            "The story must adhere to the specified parameters and avoid unnecessary details. However, transitions between events must be logical, avoiding abrupt jumps.\n" +
            "Dialogues that capture children's attention can be included. They should be short and reflect the personality of the characters.\n" +
            "At the end of the story, include a message or lesson for children naturally through the events. This message might focus on themes like \"friendship,\" \"courage,\" or \"love for nature.\"\n" +
            "Any problem-solving should involve the main character's efforts and interactions with animals or characters, emphasizing the importance of collaboration and perseverance." +
            "Return in JSON format"

     */
}