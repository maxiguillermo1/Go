package com.example.go

// This is a data class that represents a Japanese character.
// It includes a title and an image resource identifier.
data class JapaneseCharacter(val title: String, val imageResId: Int)

class JapaneseCharacters {

    // The list of Hiragana characters
    val hiraganaCharacters = listOf(
        JapaneseCharacter("a", R.drawable.a),
        JapaneseCharacter("i", R.drawable.i),
        JapaneseCharacter("u", R.drawable.u),
        JapaneseCharacter("e", R.drawable.e),
        JapaneseCharacter("o", R.drawable.o),
        // ka - n
        JapaneseCharacter("ka", R.drawable.ka),
        JapaneseCharacter("ki", R.drawable.ki),
        JapaneseCharacter("ku", R.drawable.ku),
        JapaneseCharacter("ke", R.drawable.ke),
        JapaneseCharacter("ko", R.drawable.ko),
        // sa - n
        JapaneseCharacter("sa", R.drawable.sa),
        JapaneseCharacter("shi", R.drawable.shi),
        JapaneseCharacter("su", R.drawable.su),
        JapaneseCharacter("se", R.drawable.se),
        JapaneseCharacter("so", R.drawable.so),
        // ta - n
        JapaneseCharacter("ta", R.drawable.ta),
        JapaneseCharacter("chi", R.drawable.chi),
        JapaneseCharacter("tsu", R.drawable.tsu),
        JapaneseCharacter("te", R.drawable.te),
        JapaneseCharacter("to", R.drawable.to),
        // na - n
        JapaneseCharacter("na", R.drawable.na),
        JapaneseCharacter("ni", R.drawable.ni),
        JapaneseCharacter("nu", R.drawable.nu),
        JapaneseCharacter("ne", R.drawable.ne),
        JapaneseCharacter("no", R.drawable.no),
        // ha - n
        JapaneseCharacter("ha", R.drawable.ha),
        JapaneseCharacter("hi", R.drawable.hi),
        JapaneseCharacter("fu", R.drawable.fu),
        JapaneseCharacter("he", R.drawable.he),
        JapaneseCharacter("ho", R.drawable.ho),
        // ma - n
        JapaneseCharacter("ma", R.drawable.ma),
        JapaneseCharacter("mi", R.drawable.mi),
        JapaneseCharacter("mu", R.drawable.mu),
        JapaneseCharacter("me", R.drawable.me),
        JapaneseCharacter("mo", R.drawable.mo),
        // ya - n
        JapaneseCharacter("ya", R.drawable.ya),
        // there is no "yi" in Hiragana
        JapaneseCharacter("yo", R.drawable.yo),
        // wa, wo, n
        JapaneseCharacter("wa", R.drawable.wa),
        JapaneseCharacter("wo", R.drawable.wo),
        JapaneseCharacter("n", R.drawable.n),
        // ra - n
        JapaneseCharacter("ra", R.drawable.ra),
        JapaneseCharacter("ri", R.drawable.ri),
        JapaneseCharacter("ru", R.drawable.ru),
        JapaneseCharacter("re", R.drawable.re),
        JapaneseCharacter("ro", R.drawable.ro)
    )

    // The list of Katakana characters
    val katakanaCharacters = listOf(
        JapaneseCharacter("a", R.drawable.a2),
        JapaneseCharacter("i", R.drawable.i2),
        JapaneseCharacter("u", R.drawable.u2),
        JapaneseCharacter("e", R.drawable.e2),
        JapaneseCharacter("o", R.drawable.o2),
        // ka - n
        JapaneseCharacter("ka", R.drawable.ka2),
        JapaneseCharacter("ki", R.drawable.ki2),
        JapaneseCharacter("ku", R.drawable.ku2),
        JapaneseCharacter("ke", R.drawable.ke2),
        JapaneseCharacter("ko", R.drawable.ko2),
        // sa - n
        JapaneseCharacter("sa", R.drawable.sa2),
        JapaneseCharacter("shi", R.drawable.si2),
        JapaneseCharacter("su", R.drawable.su2),
        JapaneseCharacter("se", R.drawable.se2),
        JapaneseCharacter("so", R.drawable.so2),
        // ta - n
        JapaneseCharacter("ta", R.drawable.ta2),
        JapaneseCharacter("chi", R.drawable.ti2),
        JapaneseCharacter("tsu", R.drawable.tu2),
        JapaneseCharacter("te", R.drawable.te2),
        JapaneseCharacter("to", R.drawable.to2),
        // na - n
        JapaneseCharacter("na", R.drawable.na2),
        JapaneseCharacter("ni", R.drawable.ni2),
        JapaneseCharacter("nu", R.drawable.nu2),
        JapaneseCharacter("ne", R.drawable.ne2),
        JapaneseCharacter("no", R.drawable.no2),
        // ha - n
        JapaneseCharacter("ha", R.drawable.ha2),
        JapaneseCharacter("hi", R.drawable.hi2),
        JapaneseCharacter("fu", R.drawable.hu2),
        JapaneseCharacter("he", R.drawable.he2),
        JapaneseCharacter("ho", R.drawable.ho2),
        // ma - n
        JapaneseCharacter("ma", R.drawable.ma2),
        JapaneseCharacter("mi", R.drawable.mi2),
        JapaneseCharacter("mu", R.drawable.mu2),
        JapaneseCharacter("me", R.drawable.me2),
        JapaneseCharacter("mo", R.drawable.mo2),
        // ya - n
        JapaneseCharacter("ya", R.drawable.ya2),
        JapaneseCharacter("yu", R.drawable.yu2),
        JapaneseCharacter("yo", R.drawable.yo2),
        // ra - n
        JapaneseCharacter("ra", R.drawable.ra2),
        JapaneseCharacter("ri", R.drawable.ri2),
        JapaneseCharacter("ru", R.drawable.ru2),
        JapaneseCharacter("re", R.drawable.re2),
        JapaneseCharacter("ro", R.drawable.ro2),
        // wa, wo, n
        JapaneseCharacter("wa", R.drawable.wa2),
        JapaneseCharacter("wo", R.drawable.wo2),
        JapaneseCharacter("n", R.drawable.n2)
    )
}
