package com.example.skirental.data

import com.example.skirental.models.*

class DataSource {

    companion object{
        fun createDataSetSkiuri(): ArrayList<PerecheSki>{
            var list = ArrayList<PerecheSki>()
            list.add(
                PerecheSki(
                    "Skiuri cu Legaturi Copii Atomic Redster S9 FIS J-RP2 + Colt 10 Rosu",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiSchiuri/ski11.jpg",
                    "Atomic"
                )
            )
            list.add(
                PerecheSki(
                    "Skiuri fara legaturi Volkl Deacon 76 PRO + placa si UVO 2019 Negru / Verde",
                    "The REST API course is complete. You can find the videos here: https://codingwithmitch.com/courses/build-a-rest-api/.",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiSchiuri/ski12.jpg",
                    "Volkl"
                )
            )

            list.add(
                PerecheSki(
                    "Skiuri cu Legaturi Juniori Rossignol Famous JR KX/KID-X 4 B76 2019 Alb / Argintiu",
                    "Justin has been producing online courses for YouTube, Udemy, and his website CodingForEntrepreneurs.com for over 5 years.",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiSchiuri/ski13.jpg",
                    "Rossignol"
                )
            )
            list.add(
                PerecheSki(
                    "Skiuri cu Legaturi Femei Rossignol NOVA 4 CA/XP W 10 GW Negru",
                    "Vasiliy has been a freelance android developer for several years. He also has some of the best android development courses I've had the pleasure of taking on Udemy.com.",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiSchiuri/ski14.jpg",
                    "Rossignol"
                )
            )
            list.add(
                PerecheSki(
                    "Skiuri cu Legaturi Fete Rossignol Frozen KX/KID-X 4 B76 2019 Alb / Argintiu",
                    "Freelancing as an Android developer with Donn Felker.\\r\\n\\r\\nDonn is also:\\r\\n\\r\\n1) Founder of caster.io\\r\\n\\r\\n2) Co-host of the fragmented podcast (fragmentedpodcast.com).",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiSchiuri/ski15.jpg",
                    "Rossignol"
                )
            )



            return list
        }

        fun createDataSetClapari(): ArrayList<Clapari> {
            var list = ArrayList<Clapari>()
            list.add(
                Clapari(
                    "Salomon Clapari Ski S/PRO 90 Barbati Negru",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari1.jpg",
                    "Salomon"
                )
            )
            list.add(
                Clapari(
                    "Clapari Tecnica Firebird WC 110 2019 Portocaliu",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari2.jpg",
                    "Tecnica"
                )
            )
            list.add(
                Clapari(
                    "Clapari Ski Unisex Scott Superguide Carbon 125 Lime Green/Black",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari3.jpg",
                    "Scott Superguide"
                )
            )
            return list
        }
        fun createDataSetBete(): ArrayList<Bete> {
            var list = ArrayList<Bete>()
            list.add(
                Bete(
                    "Salomon Clapari Ski S/PRO 90 Barbati Negru",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari1.jpg",
                    "Salomon"
                )
            )
            list.add(
                Bete(
                    "Clapari Tecnica Firebird WC 110 2019 Portocaliu",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari2.jpg",
                    "Tecnica"
                )
            )
            list.add(
                Bete(
                    "Clapari Ski Unisex Scott Superguide Carbon 125 Lime Green/Black",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari3.jpg",
                    "Scott Superguide"
                )
            )
            return list
        }
        fun createDataSetCasca(): ArrayList<Casca> {
            var list = ArrayList<Casca>()
            list.add(
                Casca(
                    "Salomon Clapari Ski S/PRO 90 Barbati Negru",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari1.jpg",
                    "Salomon"
                )
            )
            list.add(
                Casca(
                    "Clapari Tecnica Firebird WC 110 2019 Portocaliu",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari2.jpg",
                    "Tecnica"
                )
            )
            list.add(
                Casca(
                    "Clapari Ski Unisex Scott Superguide Carbon 125 Lime Green/Black",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari3.jpg",
                    "Scott Superguide"
                )
            )
            return list
        }
        fun createDataSetOchelari(): ArrayList<Ochelari> {
            var list = ArrayList<Ochelari>()
            list.add(
                Ochelari(
                    "Salomon Clapari Ski S/PRO 90 Barbati Negru",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari1.jpg",
                    "Salomon"
                )
            )
            list.add(
                Ochelari(
                    "Clapari Tecnica Firebird WC 110 2019 Portocaliu",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari2.jpg",
                    "Tecnica"
                )
            )
            list.add(
                Ochelari(
                    "Clapari Ski Unisex Scott Superguide Carbon 125 Lime Green/Black",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/RiceanVlad/SkiRental/main/app/src/main/res/imaginiClapari/clapari3.jpg",
                    "Scott Superguide"
                )
            )
            return list
        }
    }
}