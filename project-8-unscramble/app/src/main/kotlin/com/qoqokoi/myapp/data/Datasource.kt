package com.qoqokoi.myapp.data

import com.qoqokoi.myapp.R
import com.qoqokoi.myapp.model.Affirmation

class Datasource {
    fun loadAffirmations(): List<Affirmation> =
        listOf(
            Affirmation(R.string.affirmation1, R.drawable.poto1),
            Affirmation(R.string.affirmation2, R.drawable.poto2),
            Affirmation(R.string.affirmation3, R.drawable.poto3),
            Affirmation(R.string.affirmation4, R.drawable.poto4),
            Affirmation(R.string.affirmation5, R.drawable.poto5),
            Affirmation(R.string.affirmation6, R.drawable.poto6),
        )
}
