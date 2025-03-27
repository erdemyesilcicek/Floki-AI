package com.erdemyesilcicek.flokiai.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.erdemyesilcicek.flokiai.datas.CategoryCard

/**
 * ViewModel responsible for managing category selections across different types.
 */
class CategoryViewModel : ViewModel() {
    // Single selection categories
    private val _selectedGenre = mutableStateListOf<CategoryCard>()
    val selectedGenre: List<CategoryCard> get() = _selectedGenre

    private val _selectedSeason = mutableStateListOf<CategoryCard>()
    val selectedSeason: List<CategoryCard> get() = _selectedSeason

    // Multiple selection categories (max 3)
    private val _selectedAnimals = mutableStateListOf<CategoryCard>()
    val selectedAnimals: List<CategoryCard> get() = _selectedAnimals

    private val _selectedCharacters = mutableStateListOf<CategoryCard>()
    val selectedCharacters: List<CategoryCard> get() = _selectedCharacters

    private val _selectedFamily = mutableStateListOf<CategoryCard>()
    val selectedFamily: List<CategoryCard> get() = _selectedFamily

    private fun handleSingleSelection(category: CategoryCard, selectionList: MutableList<CategoryCard>) {
        selectionList.clear()
        selectionList.add(category)
    }

    private fun handleMultipleSelection(category: CategoryCard, selectionList: MutableList<CategoryCard>) {
        if (selectionList.contains(category)) {
            selectionList.remove(category)
        } else if (selectionList.size < 3) {
            selectionList.add(category)
        }
    }

    fun selectGenre(category: CategoryCard) {
        handleSingleSelection(category, _selectedGenre)
    }

    fun selectSeason(category: CategoryCard) {
        handleSingleSelection(category, _selectedSeason)
    }

    fun selectAnimal(category: CategoryCard) {
        handleMultipleSelection(category, _selectedAnimals)
    }

    fun selectCharacter(category: CategoryCard) {
        handleMultipleSelection(category, _selectedCharacters)
    }

    fun selectFamily(category: CategoryCard) {
        handleMultipleSelection(category, _selectedFamily)
    }

    fun clearAllSelections() {
        _selectedGenre.clear()
        _selectedSeason.clear()
        _selectedAnimals.clear()
        _selectedCharacters.clear()
        _selectedFamily.clear()
    }
}