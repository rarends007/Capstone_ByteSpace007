use sys;

drop DATABASE IF EXISTS bytespaceDB;

CREATE DATABASE bytespaceDB;

USE bytespaceDB;

-- ============================================================
-- primary key only tables
-- ============================================================
CREATE TABLE user( -- for accounts
	user_id 		    INTEGER 			PRIMARY KEY		AUTO_INCREMENT
   ,username			VARCHAR(30) 		NOT NULL
   ,firstname			VARCHAR(25)			NOT NULL
   ,middlename			VARCHAR(25) 		DEFAULT NULL
   ,lastname			VARCHAR(25) 		NOT NULL
   ,credential			VARCHAR(500)		NOT NULL
   ,employeeID			VARCHAR(10)
   ,isbanned			BOOLEAN								DEFAULT false
   ,CONSTRAINT uq_user_username UNIQUE (username)
);

CREATE TABLE action(
	action_id			INTEGER			PRIMARY KEY			AUTO_INCREMENT
   ,action_type			VARCHAR(20)								NOT NULL
   ,action_type_desc	VARCHAR(500)
);

-- ============================================================
-- foreign key relationship tables
-- ============================================================
CREATE TABLE user_role(
	user_id				INTEGER	
   ,rolename			VARCHAR(100)
   ,CONSTRAINT PRIMARY KEY(user_id, rolename)
   ,CONSTRAINT fk_user_role_user FOREIGN KEY
		(user_id) REFERENCES user(user_id)
);

CREATE TABLE post(
	post_id				INTEGER				PRIMARY KEY  	AUTO_INCREMENT
   ,user_id				INTEGER				  					NOT NULL
   ,image_file_path		VARCHAR(150)		
   ,post_text			VARCHAR(1000)
   ,CONSTRAINT fk_post_user FOREIGN KEY
		(user_id) REFERENCES user(user_id)
);


CREATE TABLE profile(
	profile_name		VARCHAR(100)		PRIMARY KEY	
   ,user_id_owned_by	INTEGER									NOT NULL
   ,profile_status		VARCHAR(100)
   ,CONSTRAINT fk_profile_user FOREIGN KEY
		(user_id_owned_by) REFERENCES user(user_id)
);

CREATE TABLE comment(
	comment_id			INTEGER				PRIMARY KEY		AUTO_INCREMENT
   ,commenting_user_id	INTEGER									NOT NULL
   ,post_id				INTEGER									NOT NULL
   ,comment_text		VARCHAR(500)											DEFAULT ""
   ,CONSTRAINT fk_comment_user FOREIGN KEY
		(commenting_user_id) REFERENCES user(user_id)
   ,CONSTRAINT fk_comment_post FOREIGN KEY
		(post_id) REFERENCES post(post_id)
);

CREATE TABLE notification(
	notification_id		INTEGER				PRIMARY KEY			AUTO_INCREMENT
   ,notification_text	VARCHAR(1000)							NOT NULL
   ,notified_user_id	INTEGER									NOT NULL
   ,CONSTRAINT fk_notificatoin_user FOREIGN KEY
		(notified_user_id) REFERENCES user(user_id)
);

CREATE TABLE message(
	message_id			INTEGER				PRIMARY KEY			AUTO_INCREMENT
   ,message_text		VARCHAR(1000)							NOT NULL
   ,sender_id			INTEGER									NOT NULL
   ,reciever_id			INTEGER									NOT NULL
   ,timestamp			DATETIME								NOT NULL
   ,CONSTRAINT fk_message_user_sender FOREIGN KEY
		(sender_id) REFERENCES user(user_id)
   ,CONSTRAINT fk_message_user_reciever FOREIGN KEY
		(reciever_id) REFERENCES user(user_id)
);

CREATE TABLE report(
	report_id			INTEGER			PRIMARY KEY				AUTO_INCREMENT
   ,report_reason 		VARCHAR(100)							NOT NULL
   ,report_title		VARCHAR(100)
   ,report_text			VARCHAR(1000)
   ,reporting_user		INTEGER									NOT NULL
   ,reported_username	VARCHAR(10)								NOT NULL
   ,CONSTRAINT fk_report_user FOREIGN KEY
		(reporting_user) REFERENCES user(user_id)
);

CREATE TABLE log(
	log_id				INTEGER			PRIMARY KEY				AUTO_INCREMENT
   ,logged_user_id		INTEGER									NOT NULL
   ,logged_action_id	INTEGER									NOT NULL
   ,log_text			VARCHAR(500)
   ,CONSTRAINT fk_log_user FOREIGN KEY
		(logged_user_id) REFERENCES user(user_id)
   ,CONSTRAINT fk_log_action FOREIGN KEY
		(logged_action_id) REFERENCES action(action_id)
);

CREATE TABLE image( -- note need to add to tables xlsx doc RA
	image_id 			INTEGER			PRIMARY KEY				AUTO_INCREMENT
   ,image_path			VARCHAR(500)	
   ,post_id				INTEGER			                        
   ,user_id 		    INTEGER									NOT NULL
   ,CONSTRAINT fk_image_post FOREIGN KEY
		(post_id) REFERENCES post(post_id)
   ,CONSTRAINT fk_image_user FOREIGN KEY
		(user_id) REFERENCES user(user_id)
);

-- =====================================================================
--  user -> All example user passwords are "test"
-- =====================================================================
INSERT INTO user (username, firstname, middlename, lastname, credential, employeeID) VALUES
('GameMaster',       'Robert',   NULL,    'King',     '922ee279a105db4ec5c7172dff2452c9$4096$b90fdc50d7741471f15dee66090715dea34eba042ee9f7450e5dcb3d8636f85a',    'EMP001'),
('sarah_m',     'Sarah',    'Marie', 'Chen',     '922ee279a105db4ec5c7172dff2452c9$4096$b90fdc50d7741471f15dee66090715dea34eba042ee9f7450e5dcb3d8636f85a',         NULL),
('PixelSlayer', 'Alex',     NULL,    'Rivera',   '922ee279a105db4ec5c7172dff2452c9$4096$b90fdc50d7741471f15dee66090715dea34eba042ee9f7450e5dcb3d8636f85a',        NULL),
('SweetLoot',   'Jennifer', NULL,    'Torres',   '922ee279a105db4ec5c7172dff2452c9$4096$b90fdc50d7741471f15dee66090715dea34eba042ee9f7450e5dcb3d8636f85a',       'EMP017'),
('VoidCoder',   'Marcus',   'James', 'Lee',      '922ee279a105db4ec5c7172dff2452c9$4096$b90fdc50d7741471f15dee66090715dea34eba042ee9f7450e5dcb3d8636f85a',        NULL),
('ZoneMapper', 'Emma',     'Rose',  'Patel',    '922ee279a105db4ec5c7172dff2452c9$4096$b90fdc50d7741471f15dee66090715dea34eba042ee9f7450e5dcb3d8636f85a',         NULL),
('JackBeast',   'Oleksandr', NULL,   'Khokhulia', '922ee279a105db4ec5c7172dff2452c9$4096$b90fdc50d7741471f15dee66090715dea34eba042ee9f7450e5dcb3d8636f85a',      NULL),
('AimBot666',   'Scout', NULL,   'Ernst', '922ee279a105db4ec5c7172dff2452c9$4096$b90fdc50d7741471f15dee66090715dea34eba042ee9f7450e5dcb3d8636f85a',              NULL),
('Kyle',   'Scout', NULL,   'Stuart', '922ee279a105db4ec5c7172dff2452c9$4096$b90fdc50d7741471f15dee66090715dea34eba042ee9f7450e5dcb3d8636f85a',                  NULL);


-- =====================================================================
--  action 
-- =====================================================================
INSERT INTO action (action_type, action_type_desc) VALUES
('LOGIN',          'Player logged into the realm'),
('LOGOUT',         'Player disconnected from session'),
('CREATE_POST',    'Player published a new memory fragment'),
('LIKE_POST',      'Player hearted / favorited a memory'),
('COMMENT',        'Player inscribed a comment rune'),
('SHARE_POST',     'Player echoed / reshared a fragment'),
('DELETE_POST',     'Player deleted their post'         ),
('UPDATE_PROFILE', 'Player altered their adventurer profile'),
('REPORT_USER',    'Player submitted a karen grief'        ),
('REPORT_CONTENT', 'Player submitted an anomaly report'),
('SEND_MESSAGE',   'Player whispered / sent a private scroll'),
('OPEN_MESSAGE',    'Player opened a message'                ),
('ADD_FRIEND',    'Player bound their fate to another adventurer'),
('REMOVE_FRIEND',    'Player unbound their fate to another adventurer');


-- =====================================================================
--  user_role
-- =====================================================================
INSERT INTO user_role (user_id, rolename) VALUES
(1, 'ADMIN'),
(2, 'MEMBER'),
(3, 'MEMBER'),
(4, 'MEMBER'),
(5, 'MEMBER'),
(6, 'MEMBER'),
(7, 'MEMBER'),
(8, 'MEMBER'),
(9, 'MEMBER');
-- =====================================================================
--  profile  (profile_name is PK)
-- =====================================================================
INSERT INTO profile (profile_name, user_id_owned_by, profile_status) VALUES
('profile_admin',       1, 'Game Site Master • watching over Bytespace'),
('profile_PixelSlayer',         2, 'cherry blossom memory collector'),
('profile_SweetLoot',           3, '3AM dungeon runner • code & synthwave'),
('profile_VoidCoder',         4, 'Dessert archivist • currently hoarding limited banners'),
('profile_ZoneMapper',           5, 'Dark theme zealot • debugging is my raid'),
('profile_sarah_m',        6, 'World explorer • new zone unlocked every season');


-- =====================================================================
--  post
-- =====================================================================
INSERT INTO post (user_id, image_file_path, post_text) VALUES
(2, 'C:/capstone/2026/media/2026/04/sakura-boss-arena.jpg',      'Cleared the Sakura Prime event — full bloom phase was gorgeous'),
(3, 'C:/capstone/2026/media/2026/04/neon-rain-dungeon.png',      'Rain + neon district at 3AM — perfect grinding spot'),
(1, NULL,                                                        'Server Notice: Please use the Report Anomaly tool instead of starting PvP in global chat.'),
(4, 'C:/capstone/2026/media/2026/04/strawberry-matcha-legendary.jpg', 'Pulled the limited Strawberry Matcha skin — worth every gem '),
(5, 'C:/capstone/2026/media/2026/04/bugslayer-trophy.png',       'Finally downed the UseAfterFree boss after 14h raid  '),
(6, 'C:/capstone/2026/media/2026/04/fjord-canyon-raid.jpg',      'New zone discovered: Fjaðrárgljúfur — 10/10 visuals, bring cold resist');


-- =====================================================================
--  comment
-- =====================================================================
INSERT INTO comment (commenting_user_id, post_id, comment_text) VALUES
(3, 1, 'Eat shit loser.'),
(4, 1, 'Missing out hurts my soul'),
(5, 2, 'Classic midnight grind playlist material right here'),
(2, 5, 'Epic! What build finally broke it?'),
(6, 5, 'I sniped that poser in VAIL.'),
(1, 3, 'Grateful to all adventurers helping keep the realm wholesome'),
(3, 6, 'That’s Fu**ing right? Iceland DLC is insane');


-- =====================================================================
--  notification
-- =====================================================================
INSERT INTO notification (notification_text, notified_user_id) VALUES
('Achievement Unlocked → Sakura Spotter I: First cherry blossom memory favorited ×5',                 2),
('SweetLoot favorited your Legendary Strawberry Matcha pull  🍓✨',                                   4),
('VoidCoder whispered about your bug kill: "gg wp on that boss"',                                   5),
('New Follower Alert: MapWanderer has joined your party!',                                          3),
('Harrassment Report filed against your PixelSlayer acct screenshot, warning',                            3),
('Realm Broadcast – GameMaster: Maintenance window in 2 hours — save & logout recommended',         1),
('Moderator Rank Up! +1 Authority Shard unlocked',                                                  4),
('Daily Quest "Share a Memory" completed → 50 XP & minor loot box',                                 5),
('Critical Comment Chain: 7 hearts landed → Title "Echo Lord" unlocked',                            6),
('Boss Defeat: Memory Leak Sovereign → +200 XP  + "Bugbane" title earned',                          5),
('New Cosmetic: PixelRain Cape auto-equipped  (+15 night-aura potency)',                            3);


-- =====================================================================
--  message 
-- =====================================================================
INSERT INTO message (message_text, sender_id, reciever_id, timestamp) VALUES
('[Party] VoidCoder → PixelSlayer: yo you suck newb',                                                               5, 3, '2026-04-12 01:14:22'),
('[Whisper] PixelSlayer → VoidCoder: Dude you suck.',                                                               3, 5, '2026-04-12 01:17:09'),
('[Guild] MatchaVault → SakuraBloom: those sakura coords are S-tier — drop a waypoint?',                            4, 2, '2026-04-12 09:42:35'),
('[Whisper] SakuraBloom → MatchaVault: Ueno Park zone IRL — spring seasonal event is peak rn',                    2, 4, '2026-04-12 09:45:11'),
('[System] GameMaster → MatchaVault: [Maintenance Alert] Realm going offline Sat 03:00–05:00 UTC for patch',          1, 4, '2026-04-12 11:20:00'),
('[Quest Update] MapWanderer → VoidCoder: New screenshot POI unlocked: kill canyon — worth the travel debuff?',   6, 5, '2026-04-12 14:55:00'),
('[Party] VoidCoder → MapWanderer: 100%. Adding to exploration journal. Pack frost potions — weather DoT is nasty',     5, 6, '2026-04-12 15:02:17'),
('[Trade] MatchaVault → SakuraBloom: WTS 3x limited matcha emotes — trading for your rare sakura filter ',           4, 2, '2026-04-12 16:10:44'),
('[Admin Whisper] GameMaster → PixelSlayer: Report on "randomacc42" processed. +25 SJW Points added',             1, 3, '2026-04-12 10:05:00');


-- =====================================================================
--  report
-- =====================================================================
INSERT INTO report (report_reason, report_title, report_text, reporting_user, reported_username) VALUES
('SPAM',           'Macro spam in global',            'Same gold-seller link posted 12× today',           5, 'VoidCoder'),
('HARRASSMENT',     'Targeted flame in thread',        'Multiple slurs directed at another player',         2, 'sarah_m'),
('MISINFORMATION', 'False cure claim in chat',        'Claiming turmeric potion cures end-game cancer',    4, 'SweetLoot');


-- =====================================================================
--  log
-- =====================================================================
INSERT INTO log (logged_user_id, logged_action_id, log_text) VALUES
(1, 1, 'GameMaster logged in from secure node'),
(2, 3, 'Published Sakura Prime event clear screenshot'),
(5, 5, 'Inscribed comment on bug-slaying trophy post'),
(3, 4, 'Favorited Neon Rain district grind screenshot'),
(1, 8, 'Opened moderation queue — 2 pending anomaly reports'),
(4, 7, 'Updated profile status → new seasonal bio');


