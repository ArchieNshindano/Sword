package com.archie.Sword.helperFunctions



val String.shouldCardSizeBeAdjusted

    get():Boolean {

        if (this.length > 204) {


            return this.length > 204

        }



        return false
    }



