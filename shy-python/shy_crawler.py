# -*- coding:utf-8 -*-
from connRedis import redis_client
import douban
import model
import config
import json
import logging
import time
import threading

def get_logger(name):
    """logger
    """
    default_logger = logging.getLogger(name)
    default_logger.setLevel(logging.DEBUG)
    stream = logging.StreamHandler()
    # stream.setLevel(logging.DEBUG)
    formatter = logging.Formatter("[%(levelname)s] %(asctime)s - %(message)s")
    stream.setFormatter(formatter)
    default_logger.addHandler(stream)
    return default_logger

logger = get_logger("douban_spider")
r = redis_client.pipeline()

class shy():
    def __init__(self,groupName,page):
        self.groupName = groupName;
        self.page = page;

    def cawal(self):
        try:
            for i in range(self.page):
                topics = douban.groupTopic(i + 1, self.groupName)
                for topic in topics:
                    if topic["photos"].__len__ !=0:
                        self.resolveTopic(topic)
                        time.sleep(10)
        except Exception as e:
            print(e)

    def resolveTopic(self,data):
        author = data["author"]
        photos = data["photos"]
        imgs = []
        user = douban.user(author["id"])

        for photo in photos:
            imgs.append(photo["alt"])
        locName = ""
        userName = ""
        alt = ""
        avatar = ""
        if "loc_name" in user:
            locName = user["loc_name"]
        if "name" in user:
            userName = user["name"];
        if "alt" in user:
            alt = user["alt"];
        if "large_avatar" in user:
            avatar = user["large_avatar"];

        post = model.haixiuzu(data["id"],data["alt"],data["title"],imgs,author["id"],userName,alt,avatar,locName,data["updated"])
        jsonStr = json.dumps(post, ensure_ascii=False, default=lambda obj: obj.__dict__)
        logger.info("topicId:%s", data["id"])

        if r.ping():
            updateTimestamp = time.mktime(time.strptime(post.updateTime, '%Y-%m-%d %H:%M:%S'))
            r.zadd("topicId", updateTimestamp, jsonStr)
            # r.set(data["id"], jsonStr)
            r.execute()
        else:
            logger.error("error redis it not connection")

    def runTask(self):
        while True:
            self.cawal();
            #time.sleep(10 * 60 * 1000)

if __name__ == '__main__':
    proSer = shy(config.GROUP_NAME, config.FETCH_PAGE)
    proSer.runTask()



