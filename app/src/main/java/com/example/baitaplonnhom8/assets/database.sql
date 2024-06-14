-- Create tables
CREATE TABLE IF NOT EXISTS TAIKHOAN (
    MATK INTEGER PRIMARY KEY ,
    HOTEN TEXT,
    CHIEUCAO REAL,
    CANNANG REAL,
    EMAIL TEXT,
    MATKHAU TEXT
);

CREATE TABLE IF NOT EXISTS MONHOC (
    MAMH INTEGER PRIMARY KEY AUTOINCREMENT,
    TENMH TEXT,
    ANHMH TEXT
);

CREATE TABLE IF NOT EXISTS TAIKHOAN_MONHOC (
    MAMH INTEGER,
    MATK INTEGER,
    FOREIGN KEY (MAMH) REFERENCES MONHOC(MAMH),
    FOREIGN KEY (MATK) REFERENCES TAIKHOAN(MATK),
    PRIMARY KEY (MAMH, MATK)
);

CREATE TABLE IF NOT EXISTS BAITAP (
    MABT INTEGER PRIMARY KEY AUTOINCREMENT,
    TENBT TEXT,
    ANHMINHHOA TEXT,
    HUONGDAN TEXT,
    THOIGIANYC INTEGER,
    THOIGIANTHUCTE INTEGER,
    TRANGTHAI TEXT,
    MAMH INTEGER,
    FOREIGN KEY (MAMH) REFERENCES MONHOC(MAMH)
);

INSERT INTO MONHOC (TENMH, ANHMH) VALUES ('Bóng chuyền', 'AnhMonHoc/volleyball.png');
INSERT INTO MONHOC (TENMH, ANHMH) VALUES ('Bóng đá', 'AnhMonHoc/football.png');
INSERT INTO MONHOC (TENMH, ANHMH) VALUES ('Chạy bền', 'AnhMonHoc/running.png');
INSERT INTO MONHOC (TENMH, ANHMH) VALUES ('Cầu lông', 'AnhMonHoc/badminton.png');
INSERT INTO MONHOC (TENMH, ANHMH) VALUES ('Tăng cơ', 'AnhMonHoc/TangCo.png');
INSERT INTO MONHOC (TENMH, ANHMH) VALUES ('Giảm mỡ', 'AnhMonHoc/GiamMo.png');
INSERT INTO MONHOC (TENMH, ANHMH) VALUES ('Duy trì thể trạng','AnhMonHoc/GiuDang.png');

INSERT INTO BAITAP (TENBT, ANHMINHHOA, HUONGDAN, THOIGIANYC, THOIGIANTHUCTE, TRANGTHAI, MAMH) VALUES
('Bump Drill', 'AnhBaiTap/Bump Drill.png', 'Practice bumping the ball. Stand with knees bent, arms extended. Focus on using forearms to direct the ball upwards.', 30, 0, 'Incomplete', 1),
('Spike Practice', 'AnhBaiTap/Bump Drill.png', 'Practice spiking the ball. Approach the net, jump, and hit the ball with an open hand, aiming for the opponent’s court.', 30, 0, 'Incomplete', 1),
('Serve Practice', 'AnhBaiTap/Bump Drill.png', 'Practice serving the ball. Stand behind the baseline, toss the ball in the air, and hit it with your hand to serve it over the net.', 30, 0, 'Incomplete', 1),
('Blocking Drill', 'AnhBaiTap/Bump Drill.png', 'Practice blocking at the net. Jump with arms extended, timing your block to intercept the opponent’s spike.', 30, 0, 'Incomplete', 1),
('Setting Drill', 'AnhBaiTap/Bump Drill.png', 'Practice setting the ball. Use fingertips to push the ball upwards, aiming for a hitter.', 30, 0, 'Incomplete', 1),
('Digging Drill', 'AnhBaiTap/Bump Drill.png', 'Practice digging the ball. Stay low, use forearms to prevent the ball from touching the ground after an opponent’s attack.', 30, 0, 'Incomplete', 1),
('Footwork Drill', 'AnhBaiTap/Bump Drill.png', 'Practice footwork. Move quickly and efficiently around the court, maintaining balance and control.', 30, 0, 'Incomplete', 1),
('Communication Drill', 'AnhBaiTap/Bump Drill.png', 'Practice team communication. Call out plays and positions to ensure coordinated team movements.', 30, 0, 'Incomplete', 1),
('Conditioning', 'AnhBaiTap/Bump Drill.png', 'Improve overall conditioning. Focus on endurance, strength, and agility exercises tailored to volleyball.', 45, 0, 'Incomplete', 1),
('Speed Drill', 'AnhBaiTap/Bump Drill.png', 'Improve speed. Perform quick sprints and agility drills to enhance reaction time on the court.', 30, 0, 'Incomplete', 1),
('Strength Training', 'AnhBaiTap/Bump Drill.png', 'Strengthen muscles used in volleyball. Include exercises like squats, lunges, and upper body workouts.', 45, 0, 'Incomplete', 1),
('Flexibility Training', 'AnhBaiTap/Bump Drill.png', 'Improve flexibility. Stretch major muscle groups to enhance range of motion and prevent injuries.', 30, 0, 'Incomplete', 1),
('Game Simulation', 'AnhBaiTap/Bump Drill.png', 'Simulate a real game. Practice game-like scenarios to improve decision-making and teamwork under pressure.', 60, 0, 'Incomplete', 1),
('Serving Accuracy Drill', 'AnhBaiTap/Bump Drill.png', 'Practice serving accuracy. Aim serves at specific targets on the opponent’s court.', 30, 0, 'Incomplete', 1),
('Defensive Drill', 'AnhBaiTap/Bump Drill.png', 'Practice defensive skills. Focus on positioning, blocking, and digging to prevent opponent’s points.', 30, 0, 'Incomplete', 1),
('Offensive Drill', 'AnhBaiTap/Bump Drill.png', 'Practice offensive skills. Focus on spiking, setting, and serving to score points against the opponent.', 30, 0, 'Incomplete', 1),
('Reaction Time Drill', 'AnhBaiTap/Bump Drill.png', 'Improve reaction time. Perform drills that require quick responses to various stimuli.', 30, 0, 'Incomplete', 1),
('Team Strategy Session', 'AnhBaiTap/Bump Drill.png', 'Discuss and practice team strategies. Plan plays and positions to maximize effectiveness.', 45, 0, 'Incomplete', 1),
('Endurance Training', 'AnhBaiTap/Bump Drill.png', 'Build endurance. Perform cardiovascular exercises to increase stamina for long matches.', 45, 0, 'Incomplete', 1),
('Cool Down', 'AnhBaiTap/Bump Drill.png', 'Cool down and stretch after practice. Perform light stretching to aid in muscle recovery and flexibility.', 20, 0, 'Incomplete', 1);


INSERT INTO BAITAP (TENBT, ANHMINHHOA, HUONGDAN, THOIGIANYC, THOIGIANTHUCTE, TRANGTHAI, MAMH) VALUES
('Dribbling Drill', 'AnhBaiTap/Footwork Drill.png', 'Practice dribbling. Use cones to set up a course, maneuver the ball with both feet, maintaining control.', 30, 0, 'Incomplete', 2),
('Shooting Practice', 'AnhBaiTap/Footwork Drill.png', 'Practice shooting. Aim for different targets in the goal, focusing on accuracy and power.', 30, 0, 'Incomplete', 2),
('Passing Drill', 'AnhBaiTap/Footwork Drill.png', 'Practice passing. Work on short and long passes with a partner, focusing on accuracy and timing.', 30, 0, 'Incomplete', 2),
('Defensive Drill', 'AnhBaiTap/Footwork Drill.png', 'Practice defensive skills. Focus on positioning, tackling, and intercepting passes to prevent opponent’s attacks.', 30, 0, 'Incomplete', 2),
('Footwork Drill', 'AnhBaiTap/Footwork Drill.png', 'Practice footwork. Improve agility and speed with ladder drills and quick directional changes.', 30, 0, 'Incomplete', 2),
('Conditioning', 'AnhBaiTap/Footwork Drill.png', 'Improve overall conditioning. Focus on endurance, strength, and agility exercises tailored to soccer.', 45, 0, 'Incomplete', 2),
('Speed Drill', 'AnhBaiTap/Footwork Drill.png', 'Improve speed. Perform sprints and agility drills to enhance quickness on the field.', 30, 0, 'Incomplete', 2),
('Strength Training', 'AnhBaiTap/Footwork Drill.png', 'Strengthen muscles used in soccer. Include exercises like squats, lunges, and core workouts.', 45, 0, 'Incomplete', 2),
('Flexibility Training', 'AnhBaiTap/Footwork Drill.png', 'Improve flexibility. Stretch major muscle groups to enhance range of motion and prevent injuries.', 30, 0, 'Incomplete', 2),
('Game Simulation', 'AnhBaiTap/Footwork Drill.png', 'Simulate a real game. Practice game-like scenarios to improve decision-making and teamwork under pressure.', 60, 0, 'Incomplete', 2),
('Corner Kick Practice', 'AnhBaiTap/Footwork Drill.png', 'Practice corner kicks. Aim for specific targets in the box, focusing on accuracy and power.', 30, 0, 'Incomplete', 2),
('Free Kick Practice', 'AnhBaiTap/Footwork Drill.png', 'Practice free kicks. Aim for different targets in the goal, focusing on accuracy and technique.', 30, 0, 'Incomplete', 2),
('Penalty Kick Practice', 'AnhBaiTap/Footwork Drill.png', 'Practice penalty kicks. Focus on accuracy, power, and placement.', 30, 0, 'Incomplete', 2),
('Goalkeeper Drill', 'AnhBaiTap/Footwork Drill.png', 'Practice goalkeeper skills. Focus on positioning, shot stopping, and distribution.', 30, 0, 'Incomplete', 2),
('Team Strategy Session', 'AnhBaiTap/Footwork Drill.png', 'Discuss and practice team strategies. Plan plays and positions to maximize effectiveness.', 45, 0, 'Incomplete', 2),
('Reaction Time Drill', 'AnhBaiTap/Footwork Drill.png', 'Improve reaction time. Perform drills that require quick responses to various stimuli.', 30, 0, 'Incomplete', 2),
('Endurance Training', 'AnhBaiTap/Footwork Drill.png', 'Build endurance. Perform cardiovascular exercises to increase stamina for long matches.', 45, 0, 'Incomplete', 2),
('Heading Practice', 'AnhBaiTap/Footwork Drill.png', 'Practice heading the ball. Focus on timing, accuracy, and power.', 30, 0, 'Incomplete', 2),
('Agility Drill', 'AnhBaiTap/Footwork Drill.png', 'Improve agility. Perform quick directional changes and ladder drills.', 30, 0, 'Incomplete', 2),
('Cool Down', 'AnhBaiTap/Footwork Drill.png', 'Cool down and stretch after practice. Perform light stretching to aid in muscle recovery and flexibility.', 20, 0, 'Incomplete', 2);


INSERT INTO BAITAP (TENBT, ANHMINHHOA, HUONGDAN, THOIGIANYC, THOIGIANTHUCTE, TRANGTHAI, MAMH) VALUES
('Warm-up Run', 'AnhBaiTap/Warm-up Run.png', 'Do a warm-up run. Start at a slow pace to gradually increase heart rate and loosen muscles.', 30, 0, 'Incomplete', 3),
('Long Distance Run', 'AnhBaiTap/Warm-up Run.png', 'Run long distances. Maintain a steady pace, focusing on endurance and proper form.', 60, 0, 'Incomplete', 3),
('Sprint Intervals', 'AnhBaiTap/Warm-up Run.png', 'Do sprint intervals. Alternate between short bursts of high-speed running and recovery periods.', 45, 0, 'Incomplete', 3),
('Hill Repeats', 'AnhBaiTap/Warm-up Run.png', 'Run up a hill repeatedly. Focus on power and strength during the ascent, and recovery on the descent.', 45, 0, 'Incomplete', 3),
('Tempo Run', 'AnhBaiTap/Warm-up Run.png', 'Run at a comfortably hard pace. Maintain a challenging speed to improve lactate threshold.', 30, 0, 'Incomplete', 3),
('Fartlek Training', 'AnhBaiTap/Warm-up Run.png', 'Incorporate speed play into your run. Alternate between fast and slow running based on how you feel.', 45, 0, 'Incomplete', 3),
('Recovery Run', 'AnhBaiTap/Warm-up Run.png', 'Do a recovery run. Run at a very easy pace to help muscles recover from harder workouts.', 30, 0, 'Incomplete', 3),
('Strength Training', 'AnhBaiTap/Warm-up Run.png', 'Strengthen muscles used in running. Focus on exercises like squats, lunges, and core workouts.', 45, 0, 'Incomplete', 3),
('Flexibility Training', 'AnhBaiTap/Warm-up Run.png', 'Improve flexibility. Stretch major muscle groups to enhance range of motion and prevent injuries.', 30, 0, 'Incomplete', 3),
('Endurance Run', 'AnhBaiTap/Warm-up Run.png', 'Run for an extended period. Focus on building endurance with a steady, moderate pace.', 60, 0, 'Incomplete', 3),
('Speed Work', 'AnhBaiTap/Warm-up Run.png', 'Incorporate speed work into your routine. Perform intervals or repeats at high intensity.', 45, 0, 'Incomplete', 3),
('Pace Run', 'AnhBaiTap/Warm-up Run.png', 'Run at your goal race pace. Practice maintaining a consistent speed over a set distance.', 30, 0, 'Incomplete', 3),
('Cool Down Run', 'AnhBaiTap/Warm-up Run.png', 'Cool down with an easy run. Gradually reduce your speed to bring your heart rate down.', 20, 0, 'Incomplete', 3),
('Cross Training', 'AnhBaiTap/Warm-up Run.png', 'Incorporate cross-training activities. Include cycling, swimming, or other low-impact exercises.', 45, 0, 'Incomplete', 3),
('Interval Training', 'AnhBaiTap/Warm-up Run.png', 'Do interval training. Alternate between high-intensity running and recovery periods.', 45, 0, 'Incomplete', 3),
('Progression Run', 'AnhBaiTap/Warm-up Run.png', 'Start at a slow pace and gradually increase speed. Finish the run at a faster pace.', 45, 0, 'Incomplete', 3),
('Trail Run', 'AnhBaiTap/Warm-up Run.png', 'Run on trails. Enjoy the varied terrain and focus on balance and strength.', 60, 0, 'Incomplete', 3),
('Group Run', 'AnhBaiTap/Warm-up Run.png', 'Run with a group. Enjoy the social aspect and motivation from running with others.', 60, 0, 'Incomplete', 3),
('Track Workout', 'AnhBaiTap/Warm-up Run.png', 'Do a track workout. Perform intervals or repeats on a track to focus on speed and form.', 45, 0, 'Incomplete', 3),
('Cool Down', 'AnhBaiTap/Warm-up Run.png', 'Cool down and stretch after running. Perform light stretching to aid in muscle recovery and flexibility.', 20, 0, 'Incomplete', 3);

INSERT INTO BAITAP (TENBT, ANHMINHHOA, HUONGDAN, THOIGIANYC, THOIGIANTHUCTE, TRANGTHAI, MAMH) VALUES
('Serve Practice', 'AnhBaiTap/Drop Shot Drill.png', 'Practice serving. Focus on accuracy and consistency, aiming for different areas of the opponent’s court.', 30, 0, 'Incomplete', 4),
('Smash Drill', 'AnhBaiTap/Drop Shot Drill.png', 'Practice smashing. Work on timing and power to hit the shuttlecock forcefully downward into the opponent’s court.', 30, 0, 'Incomplete', 4),
('Footwork Drill', 'AnhBaiTap/Drop Shot Drill.png', 'Practice footwork. Move efficiently around the court, maintaining balance and control.', 30, 0, 'Incomplete', 4),
('Drop Shot Drill', 'AnhBaiTap/Drop Shot Drill.png', 'Practice drop shots. Aim to hit the shuttlecock just over the net to make it difficult for the opponent to return.', 30, 0, 'Incomplete', 4),
('Net Play Drill', 'AnhBaiTap/Drop Shot Drill.png', 'Practice net play. Focus on quick reflexes and precise control to dominate the area near the net.', 30, 0, 'Incomplete', 4),
('Defense Drill', 'AnhBaiTap/Drop Shot Drill.png', 'Practice defensive skills. Focus on positioning, timing, and quick reactions to return smashes and drives.', 30, 0, 'Incomplete', 4),
('Clear Shot Drill', 'AnhBaiTap/Drop Shot Drill.png', 'Practice clear shots. Hit the shuttlecock high and deep into the opponent’s court to gain time to recover.', 30, 0, 'Incomplete', 4),
('Conditioning', 'AnhBaiTap/Drop Shot Drill.png', 'Improve overall conditioning. Focus on endurance, strength, and agility exercises tailored to badminton.', 45, 0, 'Incomplete', 4),
('Speed Drill', 'AnhBaiTap/Drop Shot Drill.png', 'Improve speed. Perform quick sprints and agility drills to enhance reaction time on the court.', 30, 0, 'Incomplete', 4),
('Strength Training', 'AnhBaiTap/Drop Shot Drill.png', 'Strengthen muscles used in badminton. Include exercises like squats, lunges, and upper body workouts.', 45, 0, 'Incomplete', 4),
('Flexibility Training', 'AnhBaiTap/Drop Shot Drill.png', 'Improve flexibility. Stretch major muscle groups to enhance range of motion and prevent injuries.', 30, 0, 'Incomplete', 4),
('Game Simulation', 'AnhBaiTap/Drop Shot Drill.png', 'Simulate a real game. Practice game-like scenarios to improve decision-making and teamwork under pressure.', 60, 0, 'Incomplete', 4),
('Strategy Session', 'AnhBaiTap/Drop Shot Drill.png', 'Discuss and practice strategies. Plan shots and positions to maximize effectiveness.', 45, 0, 'Incomplete', 4),
('Reaction Time Drill', 'AnhBaiTap/Drop Shot Drill.png', 'Improve reaction time. Perform drills that require quick responses to various stimuli.', 30, 0, 'Incomplete', 4),
('Endurance Training', 'AnhBaiTap/Drop Shot Drill.png', 'Build endurance. Perform cardiovascular exercises to increase stamina for long matches.', 45, 0, 'Incomplete', 4),
('Agility Drill', 'AnhBaiTap/Drop Shot Drill.png', 'Improve agility. Perform quick directional changes and ladder drills.', 30, 0, 'Incomplete', 4),
('Partner Drill', 'AnhBaiTap/Drop Shot Drill.png', 'Practice with a partner. Work on coordination and timing with drills involving two players.', 30, 0, 'Incomplete', 4),
('Serve Return Drill', 'AnhBaiTap/Drop Shot Drill.png', 'Practice returning serves. Focus on accuracy and control to gain an advantage after the opponent’s serve.', 30, 0, 'Incomplete', 4),
('Cool Down', 'AnhBaiTap/Drop Shot Drill.png', 'Cool down and stretch after practice. Perform light stretching to aid in muscle recovery and flexibility.', 20, 0, 'Incomplete', 4),
('Mental Training', 'AnhBaiTap/Drop Shot Drill.png', 'Work on mental toughness. Practice focusing techniques and strategies to stay calm under pressure.', 30, 0, 'Incomplete', 4);

INSERT INTO BAITAP (TENBT, ANHMINHHOA, HUONGDAN, THOIGIANYC, THOIGIANTHUCTE, TRANGTHAI, MAMH) VALUES
('Push-ups', 'AnhBaiTap/Push-ups.png', 'Do push-ups. Keep your body straight, lower your chest to the ground, and push back up.', 30, 0, 'Incomplete', 5),
('Weight Lifting', 'AnhBaiTap/Push-ups.png', 'Lift weights. Focus on major muscle groups with exercises like bench press, deadlift, and squats.', 45, 0, 'Incomplete', 5),
('Pull-ups', 'AnhBaiTap/Push-ups.png', 'Do pull-ups. Hang from a bar, pull your chin above the bar, and lower back down.', 30, 0, 'Incomplete', 5),
('Bicep Curls', 'AnhBaiTap/Push-ups.png', 'Do bicep curls. Use dumbbells or a barbell to lift weights, focusing on your biceps.', 30, 0, 'Incomplete', 5),
('Tricep Dips', 'AnhBaiTap/Push-ups.png', 'Do tricep dips. Lower your body by bending your elbows and push back up.', 30, 0, 'Incomplete', 5),
('Leg Press', 'AnhBaiTap/Push-ups.png', 'Use a leg press machine. Focus on pushing the weight with your legs, keeping your back supported.', 45, 0, 'Incomplete', 5),
('Bench Press', 'AnhBaiTap/Push-ups.png', 'Do bench press. Lie on a bench, lower the barbell to your chest, and push it back up.', 45, 0, 'Incomplete', 5),
('Deadlift', 'AnhBaiTap/Push-ups.png', 'Do deadlifts. Lift the barbell from the ground, keeping your back straight and using your legs.', 45, 0, 'Incomplete', 5),
('Squats', 'AnhBaiTap/Push-ups.png', 'Do squats. Lower your body by bending your knees, keeping your back straight, and stand back up.', 45, 0, 'Incomplete', 5),
('Lunges', 'AnhBaiTap/Push-ups.png', 'Do lunges. Step forward, lower your body by bending both knees, and return to the starting position.', 30, 0, 'Incomplete', 5),
('Shoulder Press', 'AnhBaiTap/Push-ups.png', 'Do shoulder press. Lift weights above your head, focusing on your shoulder muscles.', 30, 0, 'Incomplete', 5),
('Lat Pulldowns', 'AnhBaiTap/Push-ups.png', 'Do lat pulldowns. Use a machine to pull the bar down to your chest, focusing on your back muscles.', 30, 0, 'Incomplete', 5),
('Plank', 'AnhBaiTap/Push-ups.png', 'Hold a plank position. Keep your body straight, supported on your forearms and toes.', 30, 0, 'Incomplete', 5),
('Crunches', 'AnhBaiTap/Push-ups.png', 'Do crunches. Lie on your back, lift your upper body towards your knees, and lower back down.', 30, 0, 'Incomplete', 5),
('Leg Raises', 'AnhBaiTap/Push-ups.png', 'Do leg raises. Lie on your back, lift your legs up, and lower them back down without touching the ground.', 30, 0, 'Incomplete', 5),
('Calf Raises', 'AnhBaiTap/Push-ups.png', 'Do calf raises. Lift your heels off the ground, focusing on your calf muscles.', 30, 0, 'Incomplete', 5),
('Bicycle Crunches', 'AnhBaiTap/Push-ups.png', 'Do bicycle crunches. Lie on your back, lift your legs, and alternate touching your knees to your elbows.', 30, 0, 'Incomplete', 5),
('Russian Twists', 'AnhBaiTap/Push-ups.png', 'Do Russian twists. Sit on the ground, lean back, and twist your torso to each side.', 30, 0, 'Incomplete', 5),
('Cool Down', 'AnhBaiTap/Push-ups.png', 'Cool down and stretch after weight lifting. Perform light stretching to aid in muscle recovery and flexibility.', 20, 0, 'Incomplete', 5),
('Foam Rolling', 'AnhBaiTap/Push-ups.png', 'Use a foam roller. Roll over major muscle groups to release tension and aid in recovery.', 30, 0, 'Incomplete', 5);

INSERT INTO BAITAP (TENBT, ANHMINHHOA, HUONGDAN, THOIGIANYC, THOIGIANTHUCTE, TRANGTHAI, MAMH) VALUES
('Cardio', 'AnhBaiTap/Running.png', 'Do cardio exercises. Engage in activities like running, cycling, or swimming to increase heart rate and burn calories.', 45, 0, 'Incomplete', 6),
('HIIT', 'AnhBaiTap/Running.png', 'Do high-intensity interval training. Alternate between short bursts of intense exercise and recovery periods.', 30, 0, 'Incomplete', 6),
('Cycling', 'AnhBaiTap/Running.png', 'Go cycling. Ride a bike outdoors or use a stationary bike to improve cardiovascular fitness and burn fat.', 60, 0, 'Incomplete', 6),
('Jump Rope', 'AnhBaiTap/Running.png', 'Jump rope. Perform continuous jumps, focusing on maintaining a steady rhythm and increasing intensity.', 30, 0, 'Incomplete', 6),
('Running', 'AnhBaiTap/Running.png', 'Go for a run. Maintain a steady pace, aiming to increase distance and speed over time.', 45, 0, 'Incomplete', 6),
('Swimming', 'AnhBaiTap/Running.png', 'Go swimming. Engage in various strokes to work different muscle groups and improve cardiovascular fitness.', 45, 0, 'Incomplete', 6),
('Rowing', 'AnhBaiTap/Running.png', 'Use a rowing machine. Perform continuous rowing motions, focusing on full-body engagement and intensity.', 45, 0, 'Incomplete', 6),
('Stair Climbing', 'AnhBaiTap/Running.png', 'Climb stairs. Use a stair climber or actual stairs to increase heart rate and engage lower body muscles.', 30, 0, 'Incomplete', 6),
('Dance Workout', 'AnhBaiTap/Running.png', 'Do a dance workout. Follow a dance routine to improve cardiovascular fitness and burn calories.', 45, 0, 'Incomplete', 6),
('Boxing', 'AnhBaiTap/Running.png', 'Do a boxing workout. Perform punches, jabs, and footwork drills to increase heart rate and burn fat.', 30, 0, 'Incomplete', 6),
('Circuit Training', 'AnhBaiTap/Running.png', 'Do circuit training. Rotate through various exercises with minimal rest to maintain an elevated heart rate.', 45, 0, 'Incomplete', 6),
('Kickboxing', 'AnhBaiTap/Running.png', 'Do a kickboxing workout. Perform kicks, punches, and combinations to improve cardiovascular fitness and burn fat.', 45, 0, 'Incomplete', 6),
('Aerobics', 'AnhBaiTap/Running.png', 'Do an aerobics workout. Follow a choreographed routine to increase heart rate and burn calories.', 45, 0, 'Incomplete', 6),
('Elliptical Training', 'AnhBaiTap/Running.png', 'Use an elliptical machine. Engage in a low-impact cardiovascular workout to burn calories.', 45, 0, 'Incomplete', 6),
('Treadmill Workout', 'AnhBaiTap/Running.png', 'Use a treadmill. Alternate between walking, jogging, and running to maintain an elevated heart rate.', 45, 0, 'Incomplete', 6),
('Step Aerobics', 'AnhBaiTap/Running.png', 'Do step aerobics. Follow a choreographed routine using a step platform to increase intensity and burn calories.', 45, 0, 'Incomplete', 6),
('Zumba', 'AnhBaiTap/Running.png', 'Do a Zumba workout. Follow a dance-based fitness routine to improve cardiovascular fitness and burn fat.', 45, 0, 'Incomplete', 6),
('Bodyweight Exercises', 'AnhBaiTap/Running.png', 'Do bodyweight exercises. Perform movements like burpees, squats, and lunges to increase intensity and burn fat.', 30, 0, 'Incomplete', 6),
('Cool Down', 'AnhBaiTap/Running.png', 'Cool down and stretch after a cardio workout. Perform light stretching to aid in muscle recovery and flexibility.', 20, 0, 'Incomplete', 6),
('Foam Rolling', 'AnhBaiTap/Running.png', 'Use a foam roller. Roll over major muscle groups to release tension and aid in recovery.', 30, 0, 'Incomplete', 6);


INSERT INTO BAITAP (TENBT, ANHMINHHOA, HUONGDAN, THOIGIANYC, THOIGIANTHUCTE, TRANGTHAI, MAMH) VALUES
('Yoga', 'AnhBaiTap/yoga.png', 'Do yoga. Follow a yoga routine to improve flexibility, balance, and mental relaxation.', 45, 0, 'Incomplete', 7),
('Stretching', 'AnhBaiTap/yoga.png', 'Do stretching exercises. Focus on major muscle groups to enhance range of motion and prevent injuries.', 30, 0, 'Incomplete', 7),
('Light Jogging', 'AnhBaiTap/yoga.png', 'Go for a light jog. Maintain a slow, steady pace to keep your body active without overexertion.', 30, 0, 'Incomplete', 7),
('Pilates', 'AnhBaiTap/yoga.png', 'Do Pilates. Follow a routine to improve core strength, flexibility, and overall body control.', 45, 0, 'Incomplete', 7),
('Walking', 'AnhBaiTap/yoga.png', 'Go for a walk. Maintain a steady pace to keep your body active and improve cardiovascular health.', 30, 0, 'Incomplete', 7),
('Tai Chi', 'AnhBaiTap/yoga.png', 'Practice Tai Chi. Follow a series of slow, controlled movements to improve balance and mental relaxation.', 45, 0, 'Incomplete', 7),
('Meditation', 'AnhBaiTap/yoga.png', 'Practice meditation. Focus on breathing and relaxation techniques to improve mental clarity and reduce stress.', 30, 0, 'Incomplete', 7),
('Balance Training','AnhBaiTap/yoga.png', 'Do balance training exercises. Focus on stability and coordination to maintain physical health.', 30, 0, 'Incomplete', 7),
('Core Exercises', 'AnhBaiTap/yoga.png', 'Do core exercises. Perform movements like planks and crunches to strengthen your core muscles.', 30, 0, 'Incomplete', 7),
('Breathing Exercises', 'AnhBaiTap/yoga.png', 'Practice breathing exercises. Focus on deep, controlled breaths to improve lung capacity and relaxation.', 30, 0, 'Incomplete', 7),
('Foam Rolling', 'AnhBaiTap/yoga.png', 'Use a foam roller. Roll over major muscle groups to release tension and aid in recovery.', 30, 0, 'Incomplete', 7),
('Low-Impact Cardio', 'AnhBaiTap/yoga.png', 'Do low-impact cardio exercises. Engage in activities like cycling or swimming to keep active without strain.', 45, 0, 'Incomplete', 7),
('Resistance Band Exercises', 'AnhBaiTap/yoga.png'L, 'Use resistance bands. Perform exercises to strengthen muscles while maintaining low impact.', 30, 0, 'Incomplete', 7),
('Dance Workout', 'AnhBaiTap/yoga.png', 'Do a dance workout. Follow a light dance routine to keep active and have fun.', 45, 0, 'Incomplete', 7),
('Outdoor Activities', 'AnhBaiTap/yoga.png', 'Engage in outdoor activities. Enjoy hiking, gardening, or other low-intensity activities.', 60, 0, 'Incomplete', 7),
('Group Exercise Class', 'AnhBaiTap/yoga.png', 'Join a group exercise class. Participate in a class designed for maintaining fitness and social interaction.', 45, 0, 'Incomplete', 7),
('Swimming', 'AnhBaiTap/yoga.png', 'Go swimming. Enjoy a low-impact full-body workout in the water.', 45, 0, 'Incomplete', 7),
('Cycling', 'AnhBaiTap/yoga.png', 'Go cycling. Ride a bike outdoors or use a stationary bike for a low-impact workout.', 45, 0, 'Incomplete', 7),
('Cool Down', 'AnhBaiTap/yoga.png', 'Cool down and stretch after exercise. Perform light stretching to aid in muscle recovery and flexibility.', 20, 0, 'Incomplete', 7),
('Mental Training', 'AnhBaiTap/yoga.png', 'Work on mental toughness. Practice focusing techniques and strategies to stay calm and motivated.', 30, 0, 'Incomplete', 7);
