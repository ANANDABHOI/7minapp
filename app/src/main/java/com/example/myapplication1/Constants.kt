package com.example.myapplication1

object Constants {
    fun defaultExercise():ArrayList<ExerciseModel> {
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(1, "Jumping Jacks", false, R.drawable.jumping_jack, false)
        exerciseList.add(jumpingJacks)
        val wallSit = ExerciseModel(2, "Wall Sit", false, R.drawable.wall_site, false)
        exerciseList.add(wallSit)
        val pushUp = ExerciseModel(3, "Push Up", false, R.drawable.push_up, false)
        exerciseList.add(pushUp)
        val  highKneesRunning = ExerciseModel(4, "High Knees Running", false, R.drawable.high_knee_running, false)
        exerciseList.add(highKneesRunning)
        val lunge = ExerciseModel(5, "Lunge", false, R.drawable.lunge, false)
        exerciseList.add(lunge)
        val plank = ExerciseModel(6, "Plank", false, R.drawable.plank, false)
        exerciseList.add(plank)
        val legerise = ExerciseModel(7, "Ledge Raise", false, R.drawable.lege_rise, false)
        exerciseList.add(legerise)
        val squat = ExerciseModel(8, "Squat", false, R.drawable.squat, false)
        exerciseList.add(squat)
        val stepUp = ExerciseModel(9, "Step Up", false, R.drawable.step_up, false)
        exerciseList.add(stepUp)
        val pullUp = ExerciseModel(10, "Pull Up", false, R.drawable.pull_up, false)
        exerciseList.add(pullUp)
        val plankside = ExerciseModel(11, "Plank Side", false, R.drawable.plank_side, false)
        exerciseList.add(plankside)



        return exerciseList
    }

}