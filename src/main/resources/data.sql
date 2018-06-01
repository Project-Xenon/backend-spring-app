-- Make 2 dummy accounts

-- Test Account 01

WITH link_insert AS (
	INSERT INTO links (github_link, facebook_link, linkedin_link, personal_link, resume_link) VALUES (
		'https://github.com/deepankarmalhan',
		'https://www.facebook.com/DeepankarMalhan',
		'https://www.linkedin.com/in/deepankarmalhan/',
		'https://www.deepankar-malhan.info/',
		'https://www.deepankar-malhan.info/static/media/Resume.31acb91d.pdf'
	)
	RETURNING id AS links_ins_id
)
, user_insert AS (
   INSERT INTO users(first_name, last_name, current_standing, links_id, profile_email, profile_pass, profile_username)
   SELECT 'Test', 
   		'Account 01', 
   		'freshman', 
   		links_ins_id, 
   		'testaccount01@my.ccsu.edu', 
   		'$2a$10$dfzBKX4.VIYoHpwk9t0Tbe3z1YdJ8Gzn90UHHmNZTXYjkiLQ93juy', -- pass1 is the original value before hashed with bcrypt and "$2a$10$CcDLcwh7SuMS/LiPYFQgnOfiZiza3fsn37w/jqem9d07znRqnJoES" for the pass2
   		'testaccount01'
   	FROM link_insert
)
, user_skills_insert AS (
	INSERT INTO skills(user_name, technology, percent_confidence)
	VALUES ('testaccount01','Java', 90),
	('testaccount01', 'Spring (Java framework)', 40),
	('testaccount01', 'Javascript', 80),
	('testaccount01', 'ReactJS (JS library)', 90)
)
INSERT INTO userroles (user_name, role_name)
VALUES ('testaccount01', 'user'), ('testaccount01', 'admin'), ('testaccount01', 'officer');

-- Test Account 02

WITH link_insert AS (
	INSERT INTO links (github_link, facebook_link, linkedin_link) VALUES (
		'https://github.com/NilayBhatt/',
		'https://www.facebook.com/nilay.bhatt.94',
		'https://www.linkedin.com/in/nilay-bhatt-3a79a7100/'
	)
	RETURNING id AS links_ins_id
)
, user_insert AS (
   INSERT INTO users(first_name, last_name, current_standing, links_id, profile_email, profile_pass, profile_username)
   SELECT 'Test', 
   		'Account 02', 
   		'freshman', 
   		links_ins_id, 
   		'testaccount02@my.ccsu.edu', 
   		'$2a$10$CcDLcwh7SuMS/LiPYFQgnOfiZiza3fsn37w/jqem9d07znRqnJoES', -- pass1 is the original value before hashed with bcrypt
   		'testaccount02'
   	FROM link_insert
)
, user_skills_insert AS (
	INSERT INTO skills(user_name, technology, percent_confidence)
	VALUES ('testaccount02','NodeJS', 20),
	('testaccount02', 'Python', 65),
	('testaccount02', 'HTML 5', 100),
	('testaccount02', 'Bootstrap (HTML, CSS and JS library)', 50)
)
INSERT INTO userroles (user_name, role_name)
VALUES ('testaccount02', 'user'), ('testaccount02', 'admin'), ('testaccount02', 'officer');