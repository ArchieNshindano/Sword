package com.archie.Sword.helperFunctions




val String.checkLengthThenReduceSize

    get(): String {

        if (this.length > 7)
            return this.substring(0,5) + "..."

        else
            return  this

    }
