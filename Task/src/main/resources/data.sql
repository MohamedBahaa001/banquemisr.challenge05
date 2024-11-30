INSERT INTO tasks (title, description, status, priority, due_date, assigned_to)
VALUES
('Finish The Assignment', 'Finish Banque Misr Assignment', 'TODO', 'HIGH', '2024-12-01', 'mohamed'),
('Walk The Dog', 'Take your dog Rex to a walk in the park', 'IN_PROGRESS', 'MEDIUM', '2024-12-05', 'user2'),
('Clean your room', 'Room is messy and needs sweeping', 'TODO', 'LOW', '2024-12-10', 'user3'),
('Grocery Shopping', 'Buy vegetables, fruits, and snacks for the week', 'TODO', 'MEDIUM', '2024-12-03', 'user4'),
('Study for Exam', 'Study for the final exam in Mathematics', 'IN_PROGRESS', 'HIGH', '2024-12-07', 'user1'),
('Prepare Breakfast', 'Make scrambled eggs and toast for breakfast', 'TODO', 'LOW', '2024-11-26', 'user2'),
('Attend Meeting', 'Attend weekly team meeting for project updates', 'IN_PROGRESS', 'MEDIUM', '2024-11-28', 'user3'),
('Fix Computer', 'Repair the broken laptop keyboard', 'TODO', 'HIGH', '2024-12-01', 'user4'),
('Buy New Shoes', 'Purchase new pair of shoes for work', 'TODO', 'MEDIUM', '2024-12-15', 'user1'),
('Complete Code Review', 'Review the code of the new feature implemented by the team', 'IN_PROGRESS', 'HIGH', '2024-11-30', 'user2'),
('Water Plants', 'Water the house plants in the living room', 'TODO', 'LOW', '2024-12-02', 'user3'),
('Organize Desk', 'Organize desk and sort through paperwork', 'IN_PROGRESS', 'LOW', '2024-11-25', 'user4'),
('Laundry', 'Wash clothes and fold them', 'IN_PROGRESS', 'MEDIUM', '2024-12-08', 'user1'),
('Clean The Kitchen', 'Clean all surfaces and appliances in the kitchen', 'TODO', 'MEDIUM', '2024-12-06', 'user2'),
('Write Blog Post', 'Write an article on new technology trends', 'TODO', 'HIGH', '2024-12-12', 'user3'),
('Plan Vacation', 'Book flights and hotels for a weekend getaway', 'IN_PROGRESS', 'MEDIUM', '2024-12-14', 'user4'),
('Attend Workshop', 'Attend coding workshop for skill improvement', 'TODO', 'HIGH', '2024-12-13', 'user1'),
('Take Medicine', 'Take prescribed medicine for the flu', 'DONE', 'LOW', '2024-11-26', 'user2'),
('Meet with Client', 'Discuss the new project with the client', 'IN_PROGRESS', 'HIGH', '2024-12-04', 'user3'),
('Read your novel', 'Read the next chapter of the mystery novel', 'TODO', 'LOW', '2024-12-09', 'user4'),
('seed data for testing 1', 'testing the application with pre implemented seed data 1', 'TODO', 'LOW', '2024-12-09', 'mohamed'),
('seed data for testing 2', 'testing the application with pre implemented seed data 2', 'TODO', 'HIGH', '2024-12-07', 'mohamed'),
('seed data for testing 3', 'testing the application with pre implemented seed data 3', 'IN_PROGRESS', 'LOW', '2024-12-08', 'mohamed'),
('seed data for testing 4', 'testing the application with pre implemented seed data 4', 'TODO', 'HIGH', '2024-12-19', 'mohamed'),
('seed data for testing 5', 'testing the application with pre implemented seed data 5', 'IN_PROGRESS', 'MEDIUM', '2024-12-29', 'mohamed');



INSERT INTO tasks_history (task_id, title, description, status, priority, due_date, completed_by, completion_date)
VALUES
(5001, 'Design Backend', 'Create APIs for the task management system', 'DONE', 'HIGH', '2024-11-15', 'mohamed', '2024-11-27T14:30:00'),
(5002, 'Prepare Documentation', 'Write technical documentation for APIs', 'DONE', 'MEDIUM', '2024-11-20', 'mohamed', '2024-11-27T15:00:00'),
(5003, 'Code Review', 'Review code you wrote', 'DONE', 'LOW', '2024-11-25', 'mohamed', '2024-11-27T16:00:00'),
(5004, 'Fix UI Bugs', 'Fix issues in the frontend', 'DONE', 'LOW', '2024-11-22', 'Ali', '2024-11-26T10:30:00'),
(5005, 'Setup Database', 'Configure a suitable database for production', 'DONE', 'HIGH', '2024-11-18', 'Sarah', '2024-11-26T11:00:00');
