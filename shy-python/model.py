# -*- coding:utf-8 -*-
class haixiuzu(object):
    def __init__(self,id,url,title,imgs,authorId,authorName,alt,avatar,authorLocation,updateTime):
        self.id = id
        self.url = url
        self.title = title
        self.imgs = imgs
        self.authorId = authorId
        self.authorName = authorName
        self.alt = alt
        self.avatar = avatar
        self.authorLocation = authorLocation
        self.updateTime = updateTime


class user(object):
    def __init__(self, id, name, alt, avatar):
        self.id = id
        self.name = name
        self.alt = alt
        self.avatar = avatar

