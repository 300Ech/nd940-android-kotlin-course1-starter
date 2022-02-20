package com.udacity.shoestore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ValidationError
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // The actual shoe list to display
    private val shoeList = MutableLiveData<MutableList<Shoe>>()
    var shoeModel = MutableLiveData<Shoe>()

    // Contains the input validation errors
    private val _errorList = MutableLiveData<MutableList<ValidationError>>()
    val errorList: LiveData<MutableList<ValidationError>>
        get() = _errorList

    // If there is not validations errors, this value will be true, enabling the
    // "Save" button.
    private val _isModelValid = MutableLiveData<Boolean>()
    val isModelValid: LiveData<Boolean>
        get() = _isModelValid

    // This will inform the fragment that the saving is complete, to navigate
    // back to the Shoe list.
    private val _shoeSaved = MutableLiveData<Boolean>()
    val shoeSaved: LiveData<Boolean>
        get() = _shoeSaved

    init {
        shoeModel.value = getDefaultShoeItem()
        shoeList.value = mutableListOf()
        loadShoes()
    }

    private fun loadShoes() {
        val defaultShoes: MutableList<Shoe> = arrayListOf()

        defaultShoes.add(
            Shoe(
                id = 1,
                name = "Miller Cloud Sandal",
                size = 8.5,
                company = "Tory Burch",
                description = "A contoured footbed adds everyday comfort to a breezy sandal topped with an iconic logo medallion.",
                images = arrayListOf(
                    "https://n.nordstrommedia.com/id/sr3/1340a955-febe-42f2-9bb8-3aa7d1069609.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838",
                    "https://n.nordstrommedia.com/id/sr3/372b48fc-5f45-4175-b29c-aedbf44b6808.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838",
                    "https://n.nordstrommedia.com/id/sr3/a0d82c6e-3478-49e6-8f18-00fe15b68f28.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838"
                )
            )
        )

        defaultShoes.add(
            Shoe(
                id = 2,
                name = "Daybreak Sneaker",
                size = 8.0,
                company = "Nike",
                description = "Nike's 1979 Tailwind marathon shoe gets a much-hyped update in this throwback sneaker that still sports its blend of sleek nylon and high-pile suede.",
                images = arrayListOf(
                    "https://n.nordstrommedia.com/id/sr3/43b2d345-af0d-4f51-9b78-43df4de82e8a.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838",
                    "https://n.nordstrommedia.com/id/sr3/080f6e11-6543-4ec2-b077-9a23caa1ae27.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838",
                    "https://n.nordstrommedia.com/id/sr3/7512c2e4-92c2-4bf1-9def-246280021a88.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838"
                )
            )
        )

        defaultShoes.add(
            Shoe(
                id = 3,
                name = "Emerson Chelsea Boot",
                size = 8.5,
                company = "Treasure & Bond",
                description = "This Chelsea boot offers a sleek, modern vibe with its minimalist silhouette and easy pull-on style.",
                images = arrayListOf(
                    "https://n.nordstrommedia.com/id/sr3/509de70f-a75e-45f9-9224-a5052753b24c.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838",
                    "https://n.nordstrommedia.com/id/sr3/3d783897-875e-44df-8524-145f9355c3d7.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838",
                    "https://n.nordstrommedia.com/id/sr3/5c0c4f7e-e36c-45a8-8e40-a3c28980881f.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838"
                )
            )
        )

        defaultShoes.add(
            Shoe(
                id = 4,
                name = "Jessenia Ankle Strap Sandal",
                size = 7.0,
                company = "Steve Madden",
                description = "A transparent cuff and slim ankle strap enhance the modern intrigue of a minimalist sandal lifted by a high stiletto heel.",
                images = arrayListOf(
                    "https://n.nordstrommedia.com/id/sr3/aabcbd14-665b-4f92-9946-f262be271abe.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838",
                    "https://n.nordstrommedia.com/id/sr3/7dd1cd51-f41a-471c-9d06-cbf22c480d29.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838",
                    "https://n.nordstrommedia.com/id/sr3/99f2f948-8b02-4dd2-802d-1a5679b12b5e.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838"
                )
            )
        )

        defaultShoes.add(
            Shoe(
                id = 5,
                name = "Hazel Pointed Toe Pump",
                size = 8.5,
                company = "Sam Edelman",
                description = "A classic stiletto adds leg-lengthening lift and timeless appeal to an elegant pointy-toe pump.",
                images = arrayListOf(
                    "https://n.nordstrommedia.com/id/sr3/5e07daa3-8165-4aa9-b1c3-7cc6cd24d733.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838",
                    "https://n.nordstrommedia.com/id/sr3/d9a3d7a8-ee66-4b09-be49-b4fb676d33c3.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838",
                    "https://n.nordstrommedia.com/id/sr3/be0e42fc-77d8-4c93-af67-a1da02b124b9.jpeg?crop=pad&pad_color=FFF&format=jpeg&trim=color&trimcolor=FFF&w=780&h=838"
                )
            )
        )

        shoeList.value?.addAll(defaultShoes)
    }

    fun edit(id: Int) {
        if (id == 0) shoeModel.value = getDefaultShoeItem()
    }

    fun validateInput() {
        val validationErrors = mutableListOf<ValidationError>()

        if (shoeModel.value?.name.isNullOrBlank()) validationErrors.add(
            ValidationError(
                NAME_INPUT_TAG,
                "You must provide a name."
            )
        )
        if (shoeModel.value?.description.isNullOrBlank()) validationErrors.add(
            ValidationError(
                DESCRIPTION_INPUT_TAG,
                "You must provide a description"
            )
        )
        if (shoeModel.value?.company.isNullOrBlank()) validationErrors.add(
            ValidationError(
                COMPANY_INPUT_TAG,
                "You must specify a company."
            )
        )
        if (shoeModel.value?.size!! <= 0.0) validationErrors.add(
            ValidationError(
                SIZE_INPUT_TAG,
                "You must provide a size."
            )
        )

        _errorList.postValue(validationErrors)

        _isModelValid.postValue(validationErrors.size == 0)
    }

    // Saves the changes into memory.
    fun save() {
        if (_isModelValid.value == false) return

        if (shoeModel.value != null) {
            if (shoeModel.value?.id == 0) add()
        }
    }

    // Returns the default values of the model.
    private fun getDefaultShoeItem() =
        Shoe(id = 0, name = "", size = 0.0, company = "", description = "")

    // Adds a new item to the Shoe list.
    private fun add() {
        val newIndex = (shoeList.value?.maxOf { it.id } ?: 0) + 1

        shoeModel.value?.id = newIndex
        val value = shoeList.value ?: arrayListOf()
        value.add(shoeModel.value!!)
        shoeList.postValue(value)

        _shoeSaved.value = true
    }

    companion object {
        const val NAME_INPUT_TAG = "NAME_INPUT_TAG"
        const val COMPANY_INPUT_TAG = "COMPANY_INPUT_TAG"
        const val SIZE_INPUT_TAG = "SIZE_INPUT_TAG"
        const val DESCRIPTION_INPUT_TAG = "DESCRIPTION_INPUT_TAG"
    }
}