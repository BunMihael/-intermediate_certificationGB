def show_menu():
    print("Menu:")
    print('\t1 - Read notes:')
    print('\t2 - Create new note:')
    print('\t3 - Delete note:')
    print('\t4 - Edit note:')
    print('\t5 - Exit')
    try:
        return int(input('Please choose the desired item: '))
    except ValueError:
        print("You entered an invalid option. Please try again.")
        return None

def create_note():
    title = input('Enter title of the note: ')
    body = input('Enter body of the note: ')
    return title, body

def select_note():
    note_id = input('Enter note ID: ')
    return note_id

def edit_note():
    note_id = input('Enter note ID: ')
    title = input('Enter new title of the note: ')
    body = input('Enter new body of the note: ')
    return note_id, title, body

def show_notes(notes):
    for note in notes:
        print(f"Title: {note.title}, Timestamp: {note.timestamp}")
        print(f"Body: {note.body}\n")
