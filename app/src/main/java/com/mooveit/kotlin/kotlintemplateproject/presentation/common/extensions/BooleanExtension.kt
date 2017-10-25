package com.mooveit.kotlin.kotlintemplateproject.presentation.common.extensions

infix fun Boolean.then(param: () -> Unit) = if (this) param else { }
