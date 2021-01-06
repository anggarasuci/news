package com.example.skeleton_mvvm.util

object Converters {
//    fun toFormattedMoney(
//        value: String?,
//        withPrefix: Boolean = false,
//        overridePrefix: String = ""
//    ): String {
//        return value?.let {
//            if (it.isEmpty()) return ""
//
//            val moneyPrefix = if (withPrefix) {
//                if (overridePrefix.isEmpty()) Constant.DefaultMoneyPrefix
//                else overridePrefix
//            } else ""
//
//            val negativeSymbol =
//                if (value.toDouble() < 0) Constant.DefaultNegativePrefix
//                else ""
//
//
//            if (it.contains(Constant.DefaultThousandSeparator) && Validate.priceWithThousandGroup(it)) {
//                return "$moneyPrefix$this"
//            }
//
//            val decimalFormat = DecimalFormat.getIntegerInstance() as DecimalFormat
//            val formatSymbols = DecimalFormatSymbols()
//
//            formatSymbols.groupingSeparator = Constant.DefaultThousandSeparator
//            decimalFormat.decimalFormatSymbols = formatSymbols
//
//            "$negativeSymbol$moneyPrefix${decimalFormat.format(it.toDouble().absoluteValue)}"
//        } ?: ""
//    }
//
//    fun toFormattedMoney(
//        value: Double,
//        withPrefix: Boolean = false,
//        overridePrefix: String = ""
//    ): String {
//        return Converters.toFormattedMoney(String.format("%.0f", value), withPrefix, overridePrefix)
//    }
//
//    fun toFormattedMoney(
//        value: Float,
//        withPrefix: Boolean = false,
//        overridePrefix: String = ""
//    ): String {
//        return Converters.toFormattedMoney(String.format("%.0f", value), withPrefix, overridePrefix)
//    }
//
//    fun toFormattedMoney(
//        value: Int,
//        withPrefix: Boolean = false,
//        overridePrefix: String = ""
//    ): String {
//        return Converters.toFormattedMoney(value.toString(), withPrefix, overridePrefix)
//    }
//
//    fun toMoney(value: CharSequence?): Double {
//        return value?.let { v ->
//            val result =
//                v.toString().replace(Constant.DefaultThousandSeparator.toString(), "")
//                    .takeIf { it.isNotEmpty() } ?: ""
//            if (result.validNumeric()) result.toDouble()
//            else 0.0
//        } ?: 0.0
//    }
//
//    fun toMaskingPhoneNumber(value: CharSequence): String {
//        return if (value.length < 8) ""
//        else value.replaceRange(4, value.length - 4, "x".repeat(value.length - 8)).toString()
//    }

}
//
//fun <T : Message<T>> Message.Companion<T>.protoParse(intent: Intent, paramKey: String): T {
//    val bArray = intent.getByteArrayExtra(paramKey)
//    return protoUnmarshal(Unmarshaller.fromByteArray(bArray))
//}