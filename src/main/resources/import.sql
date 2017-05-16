# Insertion of 3 randomly generated users.
INSERT INTO users (username, password, enabled, birthdate, display_name, first_name, last_name, role) VALUES ('Meter Peijer', '$2a$10$Pr3hVPS2B2Ov1aYfZn/2w.1/53eMCTjmR5p89v5zGScThZ/.bkp2W', 1, '1970-01-01 00:00:00', 'MPE', 'Meter', 'Peijer', 'USER'), ('TSSE', '$2a$10$Pr3hVPS2B2Ov1aYfZn/2w.1/53eMCTjmR5p89v5zGScThZ/.bkp2W', 1, '1970-01-01 00:00:00', 'Giddy', 'TSSE', 'Sport', 'ADMIN'), ('Pinar Kutlul', '$2a$10$Pr3hVPS2B2Ov1aYfZn/2w.1/53eMCTjmR5p89v5zGScThZ/.bkp2W', 1, '1970-01-01 00:00:00', 'Lulku', 'Pinar', 'Kutlu', 'USER');

# Insertion of 10 different exercises.
INSERT INTO exercise (category, description, name) VALUES (0, 'The squat is an excellent lower body workout that targets your thighs and hips but also works the hamstrings and lower back.', 'Back squat'), (0, 'Deadlifts can be performed by anybody and are safe for everybody, however, only on the condition that one has good form; one is flexible enough to perform this great "hip extension"', 'Deadlift'), (0, 'The Bench Press is a full body, compound exercise. It works your chest, shoulders and triceps most.', 'Bench press'), (0, 'The Military Press is a shoulder exercise that focuses primarily on the deltoids, rear deltoids, and triceps. Since it is a compound exercise (meaning it uses multiple muscles), it is a great way to train your upper body.', 'Military press'), (0, 'The incline positions your arms behind you, allowing you a greater range of motion than you''d get with a standard curl, says Martin Rooney, C.S.C.S., founder of Training for Warriors.', 'Dumbbell Incline Curl'), (0, '“Dumbbells don’t lock in your arms like a barbell, so your stabilizing muscles work harder,” says Joe Dowdell, C.S.C.S., CEO of Peak Performance.', 'Neutral-Grip Dumbbell Bench Press'), (0, '“This exercise trains your core for exactly what it''s designed to do: provide stability and transfer power across multiple planes of motion,” says Dowdell.', 'Half-Kneeling Rotational Cable Chop'), (0, '“Holding the bell upside down builds shoulder stability,” says trainer Greg Robins, C.S.C.S., of Cressey Performance. And single-arm reps torch your core.', 'Half-Kneeling Single-Arm Bottom-Up Kettlebell Press'), (0, '“This is the equal but opposite exercise to the bench press,” says Robins. “You’ll correct muscle imbalances while building incredible back strength.”', 'Dumbbell Chest-Supported Row'), (0, 'While traditionally thought of as a chest exercise, this move also nails the triceps. “It allows for a greater load on the triceps than a press down,” says Robins. It’s also easier on the elbows.', 'Dumbbell Floor Press');

# Insertion of 10 workouts.
INSERT INTO workout (name, description) VALUES ('Monday', 'Some random workout.'), ('Tuesday', 'Some random workout.'), ('Wednesday', 'Some random workout.'), ('Thursday', 'Some random workout.'), ('Friday', 'Some random workout.'), ('Saturday', 'Some random workout.'), ('Sunday', 'Some random workout.'), ('Legday', 'Some random workout.'), ('Backday', 'Some random workout.'), ('Chestday', 'Some random workout.');

# Insertion of 2 well-known schedules.
INSERT INTO schedule (name, description, amount_of_trainings_per_week) VALUES ('Squat & milk challenge', 'High rep squats work wonders for building muscular bulk and strength, not just for the legs, but for the entire body.', 3), ('Wendler 531', 'This program has been built through working with many lifters and athletes and realizing that each lift needs to be trained slightly differently.', 4);