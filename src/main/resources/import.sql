
-- people
INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES  ("1987-02-15", "luciaMartinez@example.com", "Lucía", "Martínez");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1993-11-22", "marioGarcia@example.com", "Mario", "García");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("2001-07-04", "anaLopez@example.com", "Ana", "López");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1975-09-12", "joseFernandez@example.com", "José", "Fernández");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1982-03-27", "mariaSanchez@example.com", "María", "Sánchez");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1990-08-19", "pedroHernandez@example.com", "Pedro", "Hernández");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("2000-12-30", "lauraDiaz@example.com", "Laura", "Díaz");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1985-06-05", "franciscoPerez@example.com", "Francisco", "Pérez");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1998-10-10", "carmenRamirez@example.com", "Carmen", "Ramírez");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1978-04-18", "juanGonzalez@example.com", "Juan", "González");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1989-01-22", "sofiaRuiz@example.com", "Sofía", "Ruiz");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1995-03-14", "albertoTorres@example.com", "Alberto", "Torres");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1984-12-02", "isabelMartinez@example.com", "Isabel", "Martínez");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1991-09-09", "luisGomez@example.com", "Luis", "Gómez");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1997-07-25", "martaDiaz@example.com", "Marta", "Díaz");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("1986-05-15", "victorSantos@example.com", "Víctor", "Santos");

INSERT INTO dentistry.people (birthDate, email, firstName, lastName) VALUES ("2002-11-08", "juliaMoreno@example.com", "Julia", "Moreno");

-- patients

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES (NULL, "UNITEDHEALTHCARE", 1);

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES (NULL, "KAISER_PERMANENTE", 2);

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES (NULL, "ANTHEM_BLUE_CROSS_BLUE_SHIELD", 3);

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES ("mobility", "AETNA", 4);

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES (NULL, "CIGNA", 5);

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES (NULL, "HUMANA", 6);

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES (NULL, "UNITEDHEALTHCARE", 7);

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES ("hearing", "KAISER_PERMANENTE", 8);

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES (NULL, "ANTHEM_BLUE_CROSS_BLUE_SHIELD", 9);

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES (NULL, "AETNA", 10);

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES (NULL, "CIGNA", 11);

INSERT INTO dentistry.patients (disability, insuranceProvider, personalData_person_id) VALUES ("vision", "HUMANA", 12);


--schedules

INSERT INTO dentistry.schedules (endSchedule, startSchedule) VALUES ('2024-08-28 16:00:00.000', '2024-08-26 09:00:00.000');

INSERT INTO dentistry.schedules (endSchedule, startSchedule) VALUES ('2024-08-29 14:30:00.000', '2024-08-27 07:30:00.000');

INSERT INTO dentistry.schedules (endSchedule, startSchedule) VALUES ('2024-08-30 12:45:00.000', '2024-08-28 06:45:00.000');

INSERT INTO dentistry.schedules (endSchedule, startSchedule) VALUES ('2024-08-31 17:15:00.000', '2024-08-29 10:15:00.000');

INSERT INTO dentistry.schedules (endSchedule, startSchedule) VALUES ('2024-09-02 13:00:00.000', '2024-08-30 08:00:00.000');


--dentists

INSERT INTO dentistry.dentists (dentistEspecialization, personalData_person_id, schedule_id) VALUES ("ORTHODONTICS", 13, 1);

INSERT INTO dentistry.dentists (dentistEspecialization, personalData_person_id, schedule_id) VALUES ("ENDODONTICS", 14, 2);

INSERT INTO dentistry.dentists (dentistEspecialization, personalData_person_id, schedule_id) VALUES ("MAXILLOFACIAL", 15, 3);

INSERT INTO dentistry.dentists (dentistEspecialization, personalData_person_id, schedule_id) VALUES ("ORAL", 16, 4);

INSERT INTO dentistry.dentists (dentstEspecialization, personalData_person_id, schedule_id) VALUES ("PATHOLOGY", 17, 5);

--shifts

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (400, "Persistent tooth or gum pain that doesn't go away with painkillers.", '2024-09-02 10:00:00.000000', 1, 12);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (150, "Extreme tooth sensitivity to cold, hot, or sweet foods and drinks.", '2024-09-03 11:30:00.000000', 2, 1);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (220, "Gums bleeding when brushing or flossing.", '2024-09-04 09:15:00.000000', 3, 2);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (180, "Constant bad breath that doesn't improve with regular oral hygiene.", '2024-09-05 14:45:00.000000', 4, 3);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (310, "Chipped or broken teeth affecting appearance and function.", '2024-09-06 16:30:00.000000', 5, 4);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (295, "Red, swollen, or painful gums indicating possible gingivitis.", '2024-09-07 08:00:00.000000', 1, 5);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (120, "Need for a routine dental check-up to maintain good oral health.", '2024-09-09 12:30:00.000000', 2, 6);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (450, "Visible cavities or dark spots on teeth.", '2024-09-10 17:15:00.000000', 3, 7);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (275, "Feeling of loose teeth or changes in bite.", '2024-09-11 10:00:00.000000', 4, 8);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (90, "Need for professional dental cleaning to remove plaque and tartar buildup.", '2024-09-12 13:45:00.000000', 5, 9);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (400, "Pain when biting or chewing, possibly indicating a deep cavity or abscess.", '2024-09-13 08:30:00.000000', 1, 10);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (230, "Desire to whiten teeth and improve smile appearance.", '2024-09-14 15:00:00.000000', 2, 11);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (350, "Review and adjustment of dental prosthetics or orthodontic devices.", '2024-09-16 09:45:00.000000', 3, 12);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (150, "Prevention and treatment of periodontal diseases that can lead to tooth loss.", '2024-09-17 16:15:00.000000', 4, 1);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (420, "Frequent headaches or jaw pain, a possible sign of bruxism.", '2024-09-18 11:00:00.000000', 5, 2);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (310, "Dental check after an accident that may have damaged teeth or jaw.", '2024-09-19 10:30:00.000000', 1, 3);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (200, "Changes in teeth alignment affecting bite or aesthetics.", '2024-09-20 14:00:00.000000', 2, 4);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (370, "Treatment of a dental infection causing swelling and fever.", '2024-09-21 12:15:00.000000', 3, 5);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (290, "Need to extract a wisdom tooth causing discomfort.", '2024-09-23 08:45:00.000000', 4, 6);

INSERT INTO dentistry.shifts (price, reason, scheduling, dentist_id,patient_id) VALUES (480, "Consultation for cosmetic treatments like veneers, implants, or crowns.", '2024-09-24 09:30:00.000000', 5, 7);






