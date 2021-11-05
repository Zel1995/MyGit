package com.example.mygit.data.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class EventBus {
    open class Like(val id:Long)

    private val bus = PublishSubject.create<Like>()

    fun post(like: Like){
        bus.onNext(like)
    }

    fun get(): Observable<Like> = bus
}