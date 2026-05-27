package com.example.lanchtraywithnavigation.data

import com.example.lanchtraywithnavigation.R

object DataSource{
    val accomplishmentList = listOf(
        ContentData(
            title = R.string.accomplishment_lunch_roll_title,
            description = R.string.accomplishment_lunch_roll_desc,
            price = 0.50
        ),
        ContentData(
            title = R.string.accomplishment_mixed_berries_title,
            description = R.string.accomplishment_mixed_berries_desc,
            price = 1.00
        ),
        ContentData(
            title = R.string.accomplishment_pickled_veggies_title,
            description = R.string.accomplishment_pickled_veggies_desc,
            price = 0.50
        )
    )


    val sideDishList = listOf(
        ContentData(
            title = R.string.sidedish_salad_title,
            description = R.string.sidedish_salad_description,
            price = 2.50
        ),
        ContentData(
            title = R.string.sidedish_soup_title,
            description = R.string.sidedish_soup_description,
            price = 3.00
        ),
        ContentData(
            title = R.string.sidedish_potatoes_title,
            description = R.string.sidedish_potatoes_description,
            price = 2.00
        ),
        ContentData(
            title = R.string.sidedish_rice_title,
            description = R.string.sidedish_rice_description,
            price = 1.50
        )
    )

    val entreeList = listOf(
        ContentData(
            title = R.string.entree_cauliflower_title,
            description = R.string.entree_cauliflower_description,
            price = 7.00
        ),
        ContentData(
            title = R.string.entree_chili_title,
            description = R.string.entree_chili_description,
            price = 4.00
        ),
        ContentData(
            title = R.string.entree_pasta_title,
            description = R.string.entree_pasta_description,
            price = 5.50
        )
    )

}