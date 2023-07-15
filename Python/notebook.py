import os
import json
import datetime
from Note import Note
class Notebook:
    def __init__(self, filename):
        self.filename = filename
        self.notes = []
        if os.path.exists(filename):
            self._load_notes()

    def _load_notes(self):
        if os.stat(self.filename).st_size == 0:
            return []
        with open(self.filename, 'r') as f:
            data = json.load(f)
            for note in data:
                self.notes.append(Note(note['title'], note['body'], note['timestamp']))

    def _save_notes(self):
        with open(self.filename, 'w') as f:
            json.dump([note.__dict__ for note in self.notes], f)

    def add_note(self, title, body):
        self.notes.append(Note(title, body))
        self._save_notes()

    def delete_note(self, note_id):
        try:
            note_id = int(note_id)
            if note_id < 0 or note_id >= len(self.notes):
                print("Invalid note ID. Please enter a valid number.")
                return
            del self.notes[note_id]
            self._reindex_notes()
            self._save_notes()
        except ValueError:
            print("Invalid note ID. Please enter a valid number.")

    def get_notes(self):
        return self.notes

    def edit_note(self, note_id, title, body):
        try:
            note_id = int(note_id)
            if note_id < 0 or note_id >= len(self.notes):
                print("Invalid note ID. Please enter a valid number.")
                return
            note = self.notes[note_id]
            note.title = title
            note.body = body
            note.timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            self._save_notes()
        except ValueError:
            print("Invalid note ID. Please enter a valid number.")

    def _reindex_notes(self):
        for i, note in enumerate(self.notes):
            note.id = i
