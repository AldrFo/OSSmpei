package ru.acediat.feature_timetable.entities

import ru.acediat.feature_timetable.dtos.LessonsDTO

class Lesson(
    id : Int,
    time : String,
    val type : String,
    dayOfWeek: Int,
    val indicatorType : Int,
    name : String,
    val place : String,
    val teacherName : String,
    val lessonNumber : Int
) : Event(id, time, dayOfWeek, name){

    private val PRACTICE = "Практическое занятие"
    private val LECTURE = "Лекция"
    private val LAB = "Лабораторная работа"
    private val CONSULTATION = "Консультация"
    private val EXAM = "Экзамен"

    inner class Builder{

        fun buildFromDTO(lessonsDTO: LessonsDTO) : Lesson {
            with(lessonsDTO) {
                val time = "$beginLesson - $endLesson"
                val type = when(kindOfWork){
                    PRACTICE -> "Семинар"
                    LECTURE -> "Лекция"
                    LAB -> "Лабораторная"
                    CONSULTATION -> CONSULTATION
                    EXAM -> EXAM
                    else -> "Другое"
                }
                val name = discipline
                val dayOfWeek = dayOfWeek
                val teacherName = if(lecturer != "!Не определена !Вакансия ") lecturer else ""
                val lessonNumber = lessonNumberStart
                val place = auditorium
                val indicator = when(kindOfWork){
                    PRACTICE -> INDICATOR_GREEN
                    LECTURE -> INDICATOR_RED
                    LAB -> INDICATOR_YELLOW
                    CONSULTATION -> INDICATOR_GRAY
                    else -> INDICATOR_BLUE
                }
                return Lesson(
                    lessonOId, time, type,
                    dayOfWeek, indicator, name,
                    place, teacherName, lessonNumber
                )
            }
        }
    }

    companion object{
        val INDICATOR_RED = 0
        val INDICATOR_GREEN = 1
        val INDICATOR_YELLOW = 2
        val INDICATOR_BLUE = 3
        val INDICATOR_GRAY = 4
    }
}