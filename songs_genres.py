#Time - O(S*G + U*S)
#Space - O(S)
#Question not available on leet code

def FavoriteGenres(userSongs, songGenres):
    songgenre = {} 
    result = {}
#  creating a map for song to genre raltion
    for genre in songGenres.keys():
        for song in songGenres.get(genre):
            songgenre[song] = genre
    # traversing over the songs of each user
    for user in userSongs.keys():
        maximum=0
        count = {}
        for song in userSongs.get(user):
            
            music = songgenre.get(song)
            #adding the genre corresponding to the song to the count map
            if music in count.keys():
                val = count.get(music)
                val = val+1
                count[music] = val
                #finding the maximum number of songs corresponding to a particular genre
                if count.get(music) > maximum:  
                    maximum = count.get(music)
            else:
                count[music] = 1
                if count.get(music) > maximum:
                    maximum = count.get(music)
                        # print(count)
        # getting the user and the genre in a result map
        for genre in count.keys():
            if count[genre] == maximum:
                if user in result:
                    result[user].append(genre)
                else:
                    result[user] = [genre]
    return result
        


userSongs = {

                "David": ["song1", "song2", "song3", "song4", "song8"],

                "Emma": ["song5", "song6", "song7"]

            }

songGenres = {

    "Rock": ["song1", "song3"],

    "Dubstep": ["song7"],

    "Techno": ["song2", "song4"],

    "Pop": ["song5", "song6"],

    "Jazz": ["song8", "song9"]

}

print(FavoriteGenres(userSongs,songGenres))