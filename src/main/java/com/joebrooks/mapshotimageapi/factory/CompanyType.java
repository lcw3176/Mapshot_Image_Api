package com.joebrooks.mapshotimageapi.factory;

import com.joebrooks.mapshotimageapi.factory.google.GoogleMapRadius;
import com.joebrooks.mapshotimageapi.factory.kakao.KakaoMapRadius;
import lombok.Getter;

@Getter
public enum CompanyType {
    kakao(new IMapRadius[]{
            KakaoMapRadius.ONE,
            KakaoMapRadius.TWO,
            KakaoMapRadius.FIVE,
            KakaoMapRadius.TEN }),

    google(new IMapRadius[]{
            GoogleMapRadius.ONE,
            GoogleMapRadius.TWO,
            GoogleMapRadius.FIVE,
            GoogleMapRadius.TEN });

    private final IMapRadius[] mapRadius;

    private CompanyType(IMapRadius[] mapRadius){
        this.mapRadius = mapRadius;
    }
}