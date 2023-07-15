import json
import os
from note import Note

class Notebook:
    def __init__(self, filename):
        self.filename = filename
        self.notes = []
        if os.path.exists(filename):
            self._load_notes()

    def _load_notes(self):
        with open(self.filename, 'r') as f:
            data = json.load(f)
            for note in data:
                self.notes.append(Note(note['title'], note['body'], note['timestamp']))

    def _save_notes(self):
        with open(self.filename, 'w') as f:
            json.dump([note.__dict__ for note in self.notes], f)

    def create_note(self, title, body):
        self.notes.append(Note(title, body))
        self._save_notes()

    def delete_note(self, note_id):
        self.notes = [note for note in self.notes if note.title != note_id]
        self._save_notes()

    def edit_note(self, note_id, title, body):
        for note in self.notes:
            if note.title == note_id:
                note.title = title
                note.body = body
                note.timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        self._save_notes()
