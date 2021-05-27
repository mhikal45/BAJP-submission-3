package com.catalog.moviecatalogapp.utils

import com.catalog.moviecatalogapp.R
import com.catalog.moviecatalogapp.data.source.local.entity.MovieEntity
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity
import com.catalog.moviecatalogapp.data.source.remote.response.movie.*
import com.catalog.moviecatalogapp.data.source.remote.response.movie.GenresItem
import com.catalog.moviecatalogapp.data.source.remote.response.tvshow.*

object DataDummy {

    fun generateDummyMovie () : List<MovieEntity> {

        val movieList = ArrayList<MovieEntity>()

        movieList.add(
            MovieEntity(
                1724,
                "The Incredible Hulk",
                "Scientist Bruce Banner scours the planet for an antidote to the unbridled force of rage within him: the Hulk. But when the military masterminds who dream of exploiting his powers force him back to civilization, he finds himself coming face to face with a new, deadly foe.",
                "2008-06-12",
                "6.2",
                "/xfBnQ4mgf1jYZsscJGJjr6ce0Ar.jpg",
                false
        )
        )

        movieList.add( MovieEntity(
            1726,
                "Iron-Man",
            "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.",
            "2008-04-30",
            "7.6",
            R.drawable.iron_man.toString(),
            false
        )
        )

        return movieList
    }

    fun generateDummyFavoriteMovie () : List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()

        movieList.add(
            MovieEntity(
                1724,
                "The Incredible Hulk",
                "Scientist Bruce Banner scours the planet for an antidote to the unbridled force of rage within him: the Hulk. But when the military masterminds who dream of exploiting his powers force him back to civilization, he finds himself coming face to face with a new, deadly foe.",
                "2008-06-12",
                "6.2",
                "/xfBnQ4mgf1jYZsscJGJjr6ce0Ar.jpg",
                false
            )
        )
        return movieList
    }

    fun generateDummyDetailMovie () : MovieEntity {
        return MovieEntity(
            1724,
            "The Incredible Hulk",
            "Scientist Bruce Banner scours the planet for an antidote to the unbridled force of rage within him: the Hulk. But when the military masterminds who dream of exploiting his powers force him back to civilization, he finds himself coming face to face with a new, deadly foe.",
            "2008-06-12",
            "6.2",
            "/xfBnQ4mgf1jYZsscJGJjr6ce0Ar.jpg",
            false
        )
    }

    fun generateDummyTvShow () : List<TvShowEntity> {
        val showList = ArrayList<TvShowEntity>()

        showList.add(TvShowEntity(
            1403,
            "Marvel's Agents of S.H.I.E.L.D.",
            "Agent Phil Coulson of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement and Logistics Division) puts together a team of agents to investigate the new, the strange and the unknown around the globe, protecting the ordinary from the extraordinary.",
            "2013-09-24",
            "7.4",
            "/gHUCCMy1vvj58tzE3dZqeC9SXus.jpg",
            false
        ))

        return showList
    }

    fun generateDummyFavoriteTvShow() : List<TvShowEntity> {
        val showList = ArrayList<TvShowEntity>()

       showList.add(TvShowEntity(
            1403,
            "Marvel's Agents of S.H.I.E.L.D.",
           "Agent Phil Coulson of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement and Logistics Division) puts together a team of agents to investigate the new, the strange and the unknown around the globe, protecting the ordinary from the extraordinary.",
           "2013-09-24",
            "7.4",
            "/gHUCCMy1vvj58tzE3dZqeC9SXus.jpg",
            false
        ))

        return showList
    }

    fun generateDummyDetailTvShow () : TvShowEntity {
        return TvShowEntity(
            1403,
            "Marvel's Agents of S.H.I.E.L.D.",
            "Agent Phil Coulson of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement and Logistics Division) puts together a team of agents to investigate the new, the strange and the unknown around the globe, protecting the ordinary from the extraordinary.",
            "2013-09-24",
            "7.4",
            "/gHUCCMy1vvj58tzE3dZqeC9SXus.jpg",
            false
        )
    }

    fun generateDummyRemoteMovie () : List<Movie> =
        listOf(
            Movie(
                1726,
                "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.",
                "/vbY95t58MDArtyUXUIb8Fx1dCry.jpg",
                "2008-04-30",
                "Iron Man",
                7.6
            ),
            Movie(
                1724,
                "Scientist Bruce Banner scours the planet for an antidote to the unbridled force of rage within him: the Hulk. But when the military masterminds who dream of exploiting his powers force him back to civilization, he finds himself coming face to face with a new, deadly foe.",
                "/xfBnQ4mgf1jYZsscJGJjr6ce0Ar.jpg",
                "2008-06-12",
                "The Incredible Hulk",
                6.2
            )
        )

    fun generateDummyRemoteDetailMovie () : MoviesDetailResponse =
        MoviesDetailResponse(
            "The Incredible Hulk",
            listOf(
                GenresItem(
                    "Science Fiction",
                    878),
                GenresItem(
                    "Action",
                    28),
                GenresItem(
                    "Adventure",
                    12)
            ),
            1724,
            "Scientist Bruce Banner scours the planet for an antidote to the unbridled force of rage within him: the Hulk. But when the military masterminds who dream of exploiting his powers force him back to civilization, he finds himself coming face to face with a new, deadly foe.",
            "/xfBnQ4mgf1jYZsscJGJjr6ce0Ar.jpg",
            "2008-06-12",
            6.2
        )

    fun generateDummyRemoteTvShow () : List<TvShow> =
        listOf(
            TvShow(
                84958,
                "Loki",
                "2021-06-09",
                "The mercurial villain Loki resumes his role as the God of Mischief following the events of \\\"Avengers: Endgame\\\".",
                "/71kjcn5PYnNz3bMOZnGyuR4RH1V.jpg",
                0.0
            )
        )

    fun generateDummyRemoteDetailTvShow () : DetailTvShowResponse =
        DetailTvShowResponse(
            listOf(
                com.catalog.moviecatalogapp.data.source.remote.response.tvshow.GenresItem(
                    "Drama",
                    18
                ),
                com.catalog.moviecatalogapp.data.source.remote.response.tvshow.GenresItem(
                    "Sci-Fi & Fantasy",
                    10765
                ),
                com.catalog.moviecatalogapp.data.source.remote.response.tvshow.GenresItem(
                    "Action & Adventure",
                    10759
                ),
            ),
            1403,
            "2013-09-24",
            "With their backs against the wall and Nathaniel and Sibyl edging ever closer to eliminating S.H.I.E.L.D. from the history books, the agents must rely on their strengths to outsmart and outlast the Chronicoms. This is their most important fight, and it will take the help of friends and teammates, past and present, to survive.",
            "/gHUCCMy1vvj58tzE3dZqeC9SXus.jpg",
            7.4,
            "Marvel's Agents of S.H.I.E.L.D."
        )
}