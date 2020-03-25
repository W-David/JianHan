package com.example.jianhan.base;


import io.reactivex.Observable;

public interface BaseUseCase<T> {
    Observable<T> execute();
}
