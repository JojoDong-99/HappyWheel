INSERT INTO user (username, hashedPsw, firstName, lastName, birthday, brief, salt, avatar, imageName)
VALUES ('abc', 'g5Tj/qVCannMOIXtDibduhdv8nzvjkmpK1YNFVXbnQCRIvI3mVLKGmwF7suAXu48QzdoGwjcW4jdsBCkxXkE+w==', 'Ed',
        'Sheeran', '1988-03-12', 'some brief', 'Cgs9yVnCLJmwHKke8y/dbN3D407za4/hjNqtQu1YSaw=', 'image-avatar-2.png',
        'none'),
       ('Jack', '9b4d79XJGafenlrYtyYPA75sladB7uwW+BHKI6Tt28OHENRW+reT89qjp3MDcq6jx+sEnR4LoFBmPmpqXO6w9w==', 'Jack',
        'Wood', '1992-04-30', 'some brief..........', 'if6v3pmXRaONCDK5+N4/Pzm7V6KNHv8yRu+2Xgka42Y=',
        'image-avatar-1.jpg','none');

INSERT INTO article (author, title, time, content)
VALUES (1,
'Find box',
'2020-05-23 09:15:28',
'Find box a little too small and curl up with fur hanging out scratch they not getting up ever caticus cuteic with paw then. I heard this rumor where the humans are our owners, pfft, what do they know?! drool. Get scared by doggo also cucumerro chirp at birds and furrier and even more furrier hairball. Poop in a handbag look delicious and drink the  again yet warm up laptop with butt lick butt fart rainbows until owner yells pee in litter box hiss at cats sun bathe. Bite nose of your human show belly. Kick up litter why use post when this sofa is here flex claws on the human''s belly and purr like a lawnmower yet sit in window and stare oooh, a bird, yum so hunt anything that moves intently sniff hand, for scratch my tummy actually i hate you now fight me. Scratch at door to be let outside, get let out then scratch at door immmediately after to be let back in cereal boxes make for five star accommodation shake treat bag. Look at dog hiiiiiisssss jump around on couch, meow constantly until given food, attack feet why use post when this sofa is here eat a rug and furry furry hairs everywhere oh no human coming lie on counter don''t get off counter lick face hiss at owner, pee a lot, and meow repeatedly scratch at fence purrrrrr eat muffins and poutine until owner comes back. Sleep everywhere, but not in my bed my left donut is missing, as is my right. Peer out window, chatter at birds, lure them to mouth loves cheeseburgers so run outside as soon as door open but spend all night ensuring people don''t sleep sleep all day step on your keyboard while you''re gaming and then turn in a circle . Hey! you there, with the hands pee in human''s bed until he cleans the litter box. Kitty ipsum dolor sit amet, shed everywhere shed everywhere stretching attack your ankles chase the red dot, hairball run catnip eat the grass sniff get suspicious of own shadow then go play with toilette paper yet lick human with sandpaper tongue cat is love, cat is life purrr purr littel cat, little cat purr purr find something else more interesting pet me pet me pet me pet me, bite, scratch, why are you petting me.'
),
(
    1,
    'The Story of Gondolin',
    '2020-01-01',
    'As recounted in The Silmarillion, the Vala Ulmo, the Lord of Waters, revealed the location of the Vale of Tumladen to the Noldorin Lord Turgon in a dream around the year FA 50. Under this divine guidance, Turgon travelled from his kingdom in Nevrast and found the vale in FA 53. Within the Encircling Mountains just west of Dorthonion and east of the river Sirion, lay a round level plain with sheer walls on all sides and a ravine and tunnel leading out to the southwest known as the Hidden Way. In the middle of the vale there was a steep hill which was called Amon Gwareth. There Turgon decided to found a great city that would be protected by the mountains and hidden from the Dark Lord Morgoth.'
),
(
    2,
    'Warden of the West',
    '1999-12-31',
    'Lord Tywin Lannister was the head of House Lannister, Lord of Casterly Rock, Warden of the West, Lord Paramount of the Westerlands, Hand of the King for three different kings, and Protector of the Realm. He was the father of Cersei, Jaime, and Tyrion Lannister, and sole grandfather of the incest-born Joffrey, Myrcella, and Tommen Baratheon.'
),
(
    1,
    'King in the North',
    '2020-01-01',
    'Robb was the eldest child of Lady Catelyn and Lord Eddard Stark.[1] Robb was born at Riverrun and raised at Winterfell. His father left his mother the morning after their wedding to fight in Robert''s Rebellion and did not return until after Robb was born. Robb Stark was likely named after Robert Baratheon. He had two younger trueborn brothers, Bran and Rickon, and two sisters, Sansa and Arya. Robb also had a "bastard half-brother", Jon Snow, with whom Robb shared a close relationship. Jon is actually Robb''s cousin by blood, as Jon is the son of Lyanna Stark and Rhaegar Targaryen. Robb was also close to his father''s ward, Theon Greyjoy, whom he counted as his best friend outside of the family.[2] Robb had been trained from childhood to wield a sword and wear armor by Winterfell''s Master-at-Arms Ser Rodrik Cassel, and was highly proficient with both for his young age. He lived at Winterfell with his family.'
),
(
    1,
    'Elven City',
    '2015-09-05',
    'Gondolin was described as this - "Now the streets of Gondolin were paved with stone and wide, kerbed with marble, and fair houses and courts amid gardens of bright flowers were set about the ways, and many towers of great slenderness and beauty builded of white marble and carved most marvelously rose to the heaven. Squares there were lit with fountains and the home of birds that sang amid the branches of their aged trees, but of all these the greatest was that place where stood the King''s palace, and the tower thereof was the loftiest in the city, and the fountains that played before the doors shot twenty fathoms and seven in the air and fell in a singing rain of crystal; therein did the sun glitter splendidly by day, and the moon most magically shimmered by night. The birds that dwelt there were of the whiteness of snow and their voices sweeter than a lullaby of music."'
);


INSERT INTO comment (author, time, content, articleId, parent, level)
VALUES (2, '2020-06-01', 'This comment by Jack', 1, null, 0),
       (1, '2020-06-02', 'This comment by abc', 1, 1, 1),
       (2, '2020-06-05', 'This comment is under abc comment', 1, 2, 2);