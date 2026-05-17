const STORAGE_KEY = 'sms-prototype-records';
const EDITING_KEY = 'sms-prototype-editing';

const seedRecords = [
  { id: '1', rollNo: '101', name: 'Aisha Khan', section: 'A', course: 'BSc CS', department: 'Computer Science', status: 'Active' },
  { id: '2', rollNo: '102', name: 'Rahul Das', section: 'B', course: 'BCA', department: 'Information Technology', status: 'On leave' },
  { id: '3', rollNo: '103', name: 'Maya Roy', section: 'A', course: 'BSc CS', department: 'Computer Science', status: 'Active' }
];

const form = document.getElementById('student-form');
const recordIdInput = document.getElementById('record-id');
const rollNoInput = document.getElementById('roll-no');
const studentNameInput = document.getElementById('student-name');
const sectionInput = document.getElementById('section');
const courseInput = document.getElementById('course');
const departmentInput = document.getElementById('department');
const statusInput = document.getElementById('status');
const saveButton = document.getElementById('save-record');
const cancelButton = document.getElementById('cancel-edit');
const resetButton = document.getElementById('reset-demo');
const searchInput = document.getElementById('search-input');
const recordsBody = document.getElementById('records-body');
const recordsSummary = document.getElementById('records-summary');
const formTitle = document.getElementById('form-title');

let records = loadRecords();
let editingId = '';

function loadRecords() {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(seedRecords));
      return [...seedRecords];
    }

    const parsed = JSON.parse(raw);
    if (!Array.isArray(parsed) || parsed.length === 0) {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(seedRecords));
      return [...seedRecords];
    }

    return parsed;
  } catch (error) {
    console.warn('Falling back to seed data:', error);
    return [...seedRecords];
  }
}

function saveRecords(nextRecords) {
  records = nextRecords;
  localStorage.setItem(STORAGE_KEY, JSON.stringify(records));
  renderRecords();
  renderSummary();
}

function createId() {
  return `${Date.now()}-${Math.random().toString(16).slice(2, 8)}`;
}

function normalize(value) {
  return value.trim().toLowerCase();
}

function getFilteredRecords() {
  const query = normalize(searchInput.value);
  if (!query) {
    return records;
  }

  return records.filter((record) => {
    return [record.rollNo, record.name, record.section, record.course, record.department, record.status]
      .some((field) => normalize(String(field)).includes(query));
  });
}

function renderSummary() {
  const total = records.length;
  const active = records.filter((record) => record.status === 'Active').length;
  const sections = new Set(records.map((record) => record.section.trim().toUpperCase()).filter(Boolean)).size;
  const courses = new Set(records.map((record) => record.course.trim().toUpperCase()).filter(Boolean)).size;

  recordsSummary.textContent = `${total} records, ${active} active, ${sections} sections, ${courses} courses`;
}

function renderRecords() {
  const filteredRecords = getFilteredRecords();

  if (filteredRecords.length === 0) {
    recordsBody.innerHTML = `
      <tr>
        <td colspan="7">
          <div class="empty-state">No records match your search. Try a different name or roll number.</div>
        </td>
      </tr>
    `;
    return;
  }

  recordsBody.innerHTML = filteredRecords
    .map((record) => `
      <tr>
        <td>${escapeHtml(record.rollNo)}</td>
        <td>${escapeHtml(record.name)}</td>
        <td>${escapeHtml(record.section)}</td>
        <td>${escapeHtml(record.course)}</td>
        <td>${escapeHtml(record.department)}</td>
        <td><span class="pill">${escapeHtml(record.status)}</span></td>
        <td>
          <div class="row-actions">
            <button class="button ghost" type="button" data-action="edit" data-id="${record.id}">Edit</button>
            <button class="button danger" type="button" data-action="delete" data-id="${record.id}">Delete</button>
          </div>
        </td>
      </tr>
    `)
    .join('');
}

function escapeHtml(value) {
  return String(value)
    .replaceAll('&', '&amp;')
    .replaceAll('<', '&lt;')
    .replaceAll('>', '&gt;')
    .replaceAll('"', '&quot;')
    .replaceAll("'", '&#39;');
}

function resetForm() {
  form.reset();
  recordIdInput.value = '';
  editingId = '';
  saveButton.textContent = 'Add record';
  formTitle.textContent = 'Add student record';
  statusInput.value = 'Active';
  rollNoInput.focus();
}

function fillForm(record) {
  editingId = record.id;
  recordIdInput.value = record.id;
  rollNoInput.value = record.rollNo;
  studentNameInput.value = record.name;
  sectionInput.value = record.section;
  courseInput.value = record.course;
  departmentInput.value = record.department;
  statusInput.value = record.status;
  saveButton.textContent = 'Update record';
  formTitle.textContent = 'Edit student record';
}

function saveEditingState() {
  if (editingId) {
    sessionStorage.setItem(EDITING_KEY, editingId);
  } else {
    sessionStorage.removeItem(EDITING_KEY);
  }
}

function restoreEditingState() {
  const pendingId = sessionStorage.getItem(EDITING_KEY);
  if (!pendingId) {
    return;
  }

  const record = records.find((item) => item.id === pendingId);
  if (record) {
    fillForm(record);
  }
}

form.addEventListener('submit', (event) => {
  event.preventDefault();

  const nextRecord = {
    id: editingId || createId(),
    rollNo: rollNoInput.value.trim(),
    name: studentNameInput.value.trim(),
    section: sectionInput.value.trim(),
    course: courseInput.value.trim(),
    department: departmentInput.value.trim(),
    status: statusInput.value
  };

  const duplicate = records.some((record) => {
    if (editingId && record.id === editingId) {
      return false;
    }
    return record.rollNo === nextRecord.rollNo;
  });

  if (duplicate) {
    window.alert('Roll number must be unique.');
    return;
  }

  const nextRecords = editingId
    ? records.map((record) => (record.id === editingId ? nextRecord : record))
    : [nextRecord, ...records];

  saveRecords(nextRecords);
  resetForm();
  saveEditingState();
});

recordsBody.addEventListener('click', (event) => {
  const button = event.target.closest('button[data-action]');
  if (!button) {
    return;
  }

  const record = records.find((item) => item.id === button.dataset.id);
  if (!record) {
    return;
  }

  if (button.dataset.action === 'edit') {
    fillForm(record);
    saveEditingState();
    return;
  }

  if (button.dataset.action === 'delete') {
    const confirmed = window.confirm(`Delete record for ${record.name}?`);
    if (!confirmed) {
      return;
    }

    const nextRecords = records.filter((item) => item.id !== record.id);
    saveRecords(nextRecords);

    if (editingId === record.id) {
      resetForm();
      saveEditingState();
    }
  }
});

searchInput.addEventListener('input', renderRecords);

cancelButton.addEventListener('click', () => {
  resetForm();
  saveEditingState();
});

resetButton.addEventListener('click', () => {
  const confirmed = window.confirm('Restore the demo records?');
  if (!confirmed) {
    return;
  }

  localStorage.setItem(STORAGE_KEY, JSON.stringify(seedRecords));
  saveRecords([...seedRecords]);
  resetForm();
  saveEditingState();
});

renderSummary();
renderRecords();
restoreEditingState();
