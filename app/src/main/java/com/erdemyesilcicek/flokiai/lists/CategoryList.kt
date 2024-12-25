package com.erdemyesilcicek.flokiai.lists

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.erdemyesilcicek.flokiai.R
import com.erdemyesilcicek.flokiai.datas.CategoryCard

@Composable
fun getGenreList(): List<CategoryCard> {
    return listOf(
        CategoryCard(
            R.drawable.genregeminiadventure,
            stringResource(id = R.string.adventure)
        ),
        CategoryCard(
            R.drawable.genregeminifantastic,
            stringResource(id = R.string.fantastic)
        ),
        CategoryCard(
            R.drawable.genregeminimystery,
            stringResource(id = R.string.mystery)
        ),
        CategoryCard(
            R.drawable.genregeminispace,
            stringResource(id = R.string.space)
        ),
        CategoryCard(
            R.drawable.genregeminitrip,
            stringResource(id = R.string.trip)
        ),
        CategoryCard(
            R.drawable.genregeminifamily,
            stringResource(id = R.string.family)
        ),
    )
}

@Composable
fun getSeasonList(): List<CategoryCard> {
    return listOf(
        CategoryCard(
            R.drawable.seasonssummer,
            stringResource(id = R.string.summer)
        ),
        CategoryCard(
            R.drawable.seasonsspring,
            stringResource(id = R.string.spring)
        ),
        CategoryCard(
            R.drawable.seasonsautumn,
            stringResource(id = R.string.autumn)
        ),
        CategoryCard(
            R.drawable.seasonswinter,
            stringResource(id = R.string.winter)
        )
    )
}

@Composable
fun getAnimalList(): List<CategoryCard> {
    return listOf(
        CategoryCard(
            R.drawable.animalsbear,
            stringResource(id = R.string.bear)
        ),
        CategoryCard(
            R.drawable.animalsboar,
            stringResource(id = R.string.boar)
        ),
        CategoryCard(
            R.drawable.animalscamel,
            stringResource(id = R.string.camel)
        ),
        CategoryCard(
            R.drawable.animalscat,
            stringResource(id = R.string.cat)
        ),
        CategoryCard(
            R.drawable.animalschicken,
            stringResource(id = R.string.chicken)
        ),
        CategoryCard(
            R.drawable.animalscow,
            stringResource(id = R.string.cow)
        ),
        CategoryCard(
            R.drawable.animalscrocodile,
            stringResource(id = R.string.crocodile)
        ),
        CategoryCard(
            R.drawable.animalsdog,
            stringResource(id = R.string.dog)
        ),
        CategoryCard(
            R.drawable.animalselephant,
            stringResource(id = R.string.elephant)
        ),
        CategoryCard(
            R.drawable.animalsfox,
            stringResource(id = R.string.fox)
        ),
        CategoryCard(
            R.drawable.animalslion,
            stringResource(id = R.string.lion)
        ),
        CategoryCard(
            R.drawable.animalsmonkey,
            stringResource(id = R.string.monkey)
        ),
        CategoryCard(
            R.drawable.animalsseal,
            stringResource(id = R.string.seal)
        ),
        CategoryCard(
            R.drawable.animalswolf,
            stringResource(id = R.string.wolf)
        )
    )
}

@Composable
fun getCharacterList(): List<CategoryCard> {
    return listOf(
        CategoryCard(
            R.drawable.characterschef,
            stringResource(id = R.string.chef)
        ),
        CategoryCard(
            R.drawable.charactersclown,
            stringResource(id = R.string.clown)
        ),
        CategoryCard(
            R.drawable.characterscowboy,
            stringResource(id = R.string.cowboy)
        ),
        CategoryCard(
            R.drawable.charactersdwarf,
            stringResource(id = R.string.dwarf)
        ),
        CategoryCard(
            R.drawable.characterself,
            stringResource(id = R.string.elf)
        ),
        CategoryCard(
            R.drawable.charactersking,
            stringResource(id = R.string.king)
        ),
        CategoryCard(
            R.drawable.charactersninja,
            stringResource(id = R.string.ninja)
        ),
        CategoryCard(
            R.drawable.characterspirate,
            stringResource(id = R.string.pirate)
        ),
        CategoryCard(
            R.drawable.charactersthief,
            stringResource(id = R.string.thief)
        ),
        CategoryCard(
            R.drawable.characterswizard,
            stringResource(id = R.string.wizard)
        ),
    )
}

@Composable
fun getFamilyList(): List<CategoryCard> {
    return listOf(
        CategoryCard(
            R.drawable.familydad,
            stringResource(id = R.string.dad)
        ),
        CategoryCard(
            R.drawable.familymom,
            stringResource(id = R.string.mom)
        ),
        CategoryCard(
            R.drawable.familysibling,
            stringResource(id = R.string.sibling)
        ),
        CategoryCard(
            R.drawable.familypet,
            stringResource(id = R.string.pet)
        )
    )
}

/*
public val genreList = listOf(
    CategoryCard(
        R.drawable.genregeminiadventure,
        "Adventure"
    ),
    CategoryCard(
        R.drawable.genregeminifantastic,
        "Fantastic"
    ),
    CategoryCard(
        R.drawable.genregeminimystery,
        "Mystery"
    ),
    CategoryCard(
        R.drawable.genregeminispace,
        "Space"
    ),
    CategoryCard(
        R.drawable.genregeminitrip,
        "Trip"
    ),
    CategoryCard(
        R.drawable.genregeminifamily,
        "Family"
    ),
)

public val seasonList = listOf(
    CategoryCard(
        R.drawable.seasonssummer,
        "Summer"
    ),
    CategoryCard(
        R.drawable.seasonsspring,
        "Spring"
    ),
    CategoryCard(
        R.drawable.seasonsautumn,
        "Autumn"
    ),
    CategoryCard(
        R.drawable.seasonswinter,
        "Winter"
    )
)

public val animalList = listOf(
    CategoryCard(
        R.drawable.animalsbear,
        "Bear"
    ),
    CategoryCard(
        R.drawable.animalsboar,
        "Boar"
    ),
    CategoryCard(
        R.drawable.animalscamel,
        "Camel"
    ),
    CategoryCard(
        R.drawable.animalscat,
        "Cat"
    ),
    CategoryCard(
        R.drawable.animalschicken,
        "Chicken"
    ),
    CategoryCard(
        R.drawable.animalscow,
        "Cow"
    ),
    CategoryCard(
        R.drawable.animalscrocodile,
        "Crocodile"
    ),
    CategoryCard(
        R.drawable.animalsdog,
        "Dog"
    ),
    CategoryCard(
        R.drawable.animalselephant,
        "Elephant"
    ),
    CategoryCard(
        R.drawable.animalsfox,
        "Fox"
    ),
    CategoryCard(
        R.drawable.animalslion,
        "Lion"
    ),
    CategoryCard(
        R.drawable.animalsmonkey,
        "Monkey"
    ),
    CategoryCard(
        R.drawable.animalsseal,
        "Seal"
    ),
    CategoryCard(
        R.drawable.animalswolf,
        "Wolf"
    )
)

public val characterList = listOf(
    CategoryCard(
        R.drawable.characterschef,
        "Chef"
    ),
    CategoryCard(
        R.drawable.charactersclown,
        "Clown"
    ),
    CategoryCard(
        R.drawable.characterscowboy,
        "Cowboy"
    ),
    CategoryCard(
        R.drawable.charactersdwarf,
        "Dwarf"
    ),
    CategoryCard(
        R.drawable.characterself,
        "Elf"
    ),
    CategoryCard(
        R.drawable.charactersking,
        "King"
    ),
    CategoryCard(
        R.drawable.charactersninja,
        "Ninja"
    ),
    CategoryCard(
        R.drawable.characterspirate,
        "Pirate"
    ),
    CategoryCard(
        R.drawable.charactersthief,
        "Thief"
    ),
    CategoryCard(
        R.drawable.characterswizard,
        "Wizard"
    ),
)

public val familyList = listOf(
    CategoryCard(
        R.drawable.familydad,
        "Dad"
    ),
    CategoryCard(
        R.drawable.familymom,
        "Mom"
    ),
    CategoryCard(
        R.drawable.familysibling,
        "Sibling"
    ),
    CategoryCard(
        R.drawable.familypet,
        "Pet"
    )
)
 */