import datetime

class Note:
    def __init__(self, title, body, timestamp=None):
        self.title = title
        self.body = body
        if timestamp:
            self.timestamp = timestamp
        else:
            self.timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
