# -*- coding:utf-8 -*-
import config
import urllib
import urllib.request
import json
import time
from random import choice
from helper import gen_bids
from shy_crawler import logger

class HTTPError(Exception):

    """ HTTP状态码不是200异常 """
    def __init__(self, status_code, url):
        self.status_code = status_code
        self.url = url
        time.sleep(20 * 60 * 1000)

    def __str__(self):
        return "%s HTTP %s" % (self.url, self.status_code)

class URLFetchError(Exception):

    """ HTTP请求结果为空异常 """

    def __init__(self, url):
        self.url = url

    def __str__(self):
        return "%s fetch failed!" % self.self.url

def groupTopic(page, groupName):
    start = (page - 1) * 20
    url = getUrl("/group/" + groupName + "/topics", {'start': start})
    data = getData(url)
    if "code" in data:
        return []
    return getData(url)["topics"]

def getUrl(path, query):
    path = config.API_ROOT + path
    path = path + "?" + urllib.parse.urlencode(query)
    return path

def getUserUrl(path):
    path = config.API_ROOT + path
    return path

def user(userId):
    url = getUserUrl("/user/" + userId);
    return getData(url)

def getData(url, retury_num=10):
    resp = None
    for i in range(retury_num):
        try:
            headers = {'Cookie':'bid="%s"' % choice(gen_bids()),'User-Agent':'Baiduspider','Accept-Language':'zh-CN,zh'}
            req = urllib.request.Request(url,None,headers)
            resp = urllib.request.urlopen(req)
            if resp.code != 200:
                raise HTTPError(resp.code, url)
            content = resp.read()
            data = json.loads(content.decode("utf8"))
            break
        except Exception as e:
            logger.warn("%s %d failed!\n%s", url, i, str(e))
            time.sleep(2)
    if resp is None:
        raise URLFetchError(url)
    return data