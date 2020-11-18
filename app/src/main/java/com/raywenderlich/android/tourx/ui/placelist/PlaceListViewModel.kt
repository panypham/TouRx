/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.tourx.ui.placelist

import androidx.hilt.lifecycle.ViewModelInject
import com.raywenderlich.android.tourx.BaseViewModel
import com.raywenderlich.android.tourx.entities.Places
import com.raywenderlich.android.tourx.networking.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PlaceListViewModel
@ViewModelInject constructor(private val service: ApiService) : BaseViewModel<Places>() {

  /**
   * Uses [Observable.ambWith] to combine two [Observable] so only the first [Observable] to emit gets relayed.
   */
  fun loadTheQuickestOne() {
  }

  /**
   * Uses [Observable.zip] underneath so waits for all the [Observable] to complete.
   */
  fun loadAllAtOnce() {
  }


  /**
   * Emits as soon as there is data available.
   */
  fun loadOnReceive() {
    startLoading()
    service.fetchEarthPlaces()
        .mergeWith(service.fetchMarsPlaces())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { recordStartTime() }
        .subscribeBy(
            onNext = onDataLoadHandler,
            onError = onErrorHandler,
            onComplete = onCompleteHandler
        ).addTo(disposable)
  }


  /**
   * Waits for all the [Observable] to complete before relaying the erroneous [Observable] down the chain.
   */
  fun loadExperimental() {
  }

  fun demonstrateSwitchOnNext() {
  }

  fun demonstrateJoinBehavior() {
  }


  fun demonstrateGroupJoin() {
  }
}