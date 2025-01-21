package com.erdemyesilcicek.flokiai.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.erdemyesilcicek.flokiai.datas.CategoryCard

class CategoryViewModel : ViewModel() {
    private val _selectedGenre = mutableStateListOf<CategoryCard>()
    val selectedGenre: List<CategoryCard> get() = _selectedGenre

    private val _selectedSeason = mutableStateListOf<CategoryCard>()
    val selectedSeason: List<CategoryCard> get() = _selectedSeason

    private val _selectedAnimals = mutableStateListOf<CategoryCard>()
    val selectedAnimals: List<CategoryCard> get() = _selectedAnimals

    private val _selectedCharacters = mutableStateListOf<CategoryCard>()
    val selectedCharacters: List<CategoryCard> get() = _selectedCharacters

    private val _selectedFamily = mutableStateListOf<CategoryCard>()
    val selectedFamily: List<CategoryCard> get() = _selectedFamily

    /*
    fun onSingleSelection(category: CategoryCard, selectedList: MutableList<CategoryCard>) {
        selectedList.clear()
        selectedList.add(category)
    }

    fun onMultipleSelection(category: CategoryCard, selectedList: MutableList<CategoryCard>) {
        if (selectedList.contains(category)) {
            selectedList.remove(category)
        } else if (selectedList.size < 3) {
            selectedList.add(category)
        }
    }

     */

    fun selectGenre(category: CategoryCard) {
        _selectedGenre.clear()
        _selectedGenre.add(category)
    }

    fun selectSeason(category: CategoryCard) {
        _selectedSeason.clear()
        _selectedSeason.add(category)
    }

    fun selectAnimal(category: CategoryCard) {
        if (_selectedAnimals.contains(category)) {
            _selectedAnimals.remove(category)
        } else if (_selectedAnimals.size < 3) {
            _selectedAnimals.add(category)
        }
    }

    fun selectCharacter(category: CategoryCard) {
        if (_selectedCharacters.contains(category)) {
            _selectedCharacters.remove(category)
        } else if (_selectedCharacters.size < 3) {
            _selectedCharacters.add(category)
        }
    }

    fun selectFamily(category: CategoryCard) {
        if (_selectedFamily.contains(category)) {
            _selectedFamily.remove(category)
        } else if (_selectedFamily.size < 3) {
            _selectedFamily.add(category)
        }
    }

    fun clearAllSelections() {
        _selectedGenre.clear()
        _selectedSeason.clear()
        _selectedAnimals.clear()
        _selectedCharacters.clear()
        _selectedFamily.clear()
    }
}

