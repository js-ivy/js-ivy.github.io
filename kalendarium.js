"use strict";

const MONTH_NAMES = [
    "Styczeń",
    "Luty",
    "Marzec",
    "Kwiecień",
    "Maj",
    "Czerwiec",
    "Lipiec",
    "Sierpień",
    "Wrzesień",
    "Październik",
    "Listopad",
    "Grudzień"
];

const WEEKDAY_NAMES = [
    "poniedziałek",
    "wtorek",
    "środę",
    "czwartek",
    "piątek",
    "sobotę",
    "niedzielę"
];

const TASKS = [
    {
        id: 1,
        published: "2026-10-18",
        firstDays: 14,
        secondDays: 14
    },
    {
        id: 2,
        published: "2026-10-25",
        firstDays: 14,
        secondDays: 14
    },
    {
        id: 3,
        published: "2026-10-25",
        firstDays: 14,
        secondDays: 14
    },
    {
        id: 4,
        published: "2026-11-01",
        firstDays: 14,
        secondDays: 14
    },
    {
        id: 5,
        published: "2026-11-08",
        firstDays: 14,
        secondDays: 14
    },
    {
        id: 6,
        published: "2026-11-15",
        firstDays: 14,
        secondDays: 14
    },
    {
        id: 7,
        published: "2026-11-22",
        firstDays: 14,
        secondDays: 14
    },
    {
        id: 8,
        published: "2026-11-29",
        firstDays: 14,
        secondDays: 14
    },
    {
        id: 9,
        published: "2026-12-06",
        firstDays: 14,
        secondDays: 14
    },
    {
        id: 10,
        published: "2026-12-13",
        firstDays: 14,
        secondDays: 14
    },
    {
        id: 11,
        published: "2026-12-20",
        firstDays: 14,
        secondDays: 21
    },
    {
        id: 12,
        published: "2026-12-20",
        firstDays: 14,
        secondDays: 21
    }
];

function parseDate(dateString) {
    const [year, month, day] = dateString
        .split("-")
        .map(Number);

    return new Date(year, month - 1, day);
}

function addDays(date, days) {
    const result = new Date(
        date.getFullYear(),
        date.getMonth(),
        date.getDate()
    );

    result.setDate(result.getDate() + days);

    return result;
}

function dateKey(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");

    return `${year}-${month}-${day}`;
}

function sameDate(a, b) {
    return dateKey(a) === dateKey(b);
}

function formatDate(date) {
    return new Intl.DateTimeFormat("pl-PL", {
        day: "numeric",
        month: "long",
        year: "numeric"
    }).format(date);
}

function formatShortDate(date) {
    return new Intl.DateTimeFormat("pl-PL", {
        day: "2-digit",
        month: "2-digit",
        year: "numeric"
    }).format(date);
}

const SCHEDULE = TASKS.map(task => {
    const publicationDate = parseDate(task.published);

    const firstDeadline = addDays(
        publicationDate,
        task.firstDays
    );

    const secondDeadline = addDays(
        firstDeadline,
        task.secondDays
    );

    return {
        ...task,

        publicationDate,
        firstDeadline,
        secondDeadline,

        firstStart: publicationDate,
        firstEnd: firstDeadline,

        secondStart: addDays(firstDeadline, 1),
        secondEnd: secondDeadline
    };
});

const monthTitle = document.getElementById("month-title");
const monthSubtitle = document.getElementById("month-subtitle");

const calendarWeeks = document.getElementById("calendar-weeks");

const previousButton = document.getElementById("previous-month");
const nextButton = document.getElementById("next-month");
const todayButton = document.getElementById("today-button");

const taskCount = document.getElementById("task-count");

const MIN_MONTH = new Date(2026, 9, 1);
const MAX_MONTH = new Date(2027, 0, 1);

let displayedMonth = new Date(2026, 9, 1);

function monthKey(date) {
    return date.getFullYear() * 12 + date.getMonth();
}

function compareMonths(a, b) {
    return monthKey(a) - monthKey(b);
}

function getCalendarStart(year, month) {
    const firstDay = new Date(year, month, 1);

    const weekday = firstDay.getDay();

    const mondayOffset = (weekday + 6) % 7;

    return addDays(firstDay, -mondayOffset);
}

function getDayBars(date) {
    const bars = [];

    for (const task of SCHEDULE) {

        if (
            date >= task.firstStart &&
            date <= task.firstEnd
        ) {
            bars.push({
                task,
                type: "first",
                start: task.firstStart,
                end: task.firstEnd
            });
        }

        if (
            date >= task.secondStart &&
            date <= task.secondEnd
        ) {
            bars.push({
                task,
                type: "second",
                start: task.secondStart,
                end: task.secondEnd
            });
        }
    }

    bars.sort((a, b) => {
        if (a.task.id !== b.task.id) {
            return a.task.id - b.task.id;
        }

        return a.type === "first" ? -1 : 1;
    });

    return bars;
}

function createEvent(event) {
    const element = document.createElement("div");

    element.classList.add("day-event");

    if (event.type === "publication") {
        element.classList.add("publication-event");

        element.textContent = `Publ. Z${event.task.id}`;

        element.title =
            `Publikacja zadania ${event.task.id}: ` +
            formatDate(event.task.publicationDate);

    } else if (event.type === "first") {
        element.classList.add("first-deadline-event");

        element.textContent = `I term. Z${event.task.id}`;

        element.title =
            `Pierwszy termin zadania ${event.task.id}: ` +
            formatDate(event.task.firstDeadline);

    } else {
        element.classList.add("second-deadline-event");

        element.textContent = `II term. Z${event.task.id}`;

        element.title =
            `Drugi termin zadania ${event.task.id}: ` +
            formatDate(event.task.secondDeadline);
    }

    return element;
}

function createTaskBar(bar, date) {
    const element = document.createElement("div");

    element.classList.add(
        "task-bar",
        bar.type,
        `task-${bar.task.id}`
    );

    const isPublicationDay = sameDate(
        date,
        bar.task.publicationDate
    );

    const isSecondTermStart = sameDate(
        date,
        bar.task.secondStart
    );

    const isDeadline = sameDate(
        date,
        bar.end
    );

    const deadlineNumber = bar.type === "first" ? "I" : "II";

    if (
        (bar.type === "first" && isPublicationDay) ||
        (bar.type === "second" && isSecondTermStart)
    ) {
        element.classList.add("segment-label");

        element.textContent =
            `${deadlineNumber} termin — Zadanie ${bar.task.id}`;
    } else {
        element.classList.add("segment-continuation");
        element.setAttribute("aria-hidden", "true");
    }

    if (isDeadline) {
        element.classList.add("deadline-day", "deadline-label");

        element.textContent =
            `${deadlineNumber} termin — Zadanie ${bar.task.id}`;

        element.classList.remove(
            "segment-label",
            "segment-continuation"
        );

        element.removeAttribute("aria-hidden");
    }

    // Podpowiedź po najechaniu kursorem.
    const deadline = bar.type === "first"
        ? bar.task.firstDeadline
        : bar.task.secondDeadline;

    element.title =
        `Zadanie ${bar.task.id} — ` +
        `${bar.type === "first" ? "I termin" : "II termin"}. ` +
        `Termin: ${formatDate(deadline)}. ` +
        `Okres: ${formatShortDate(bar.start)} – ${formatShortDate(bar.end)}.`;

    return element;
}

function createDay(date, displayedYear, displayedMonthNumber) {
    const element = document.createElement("div");

    element.classList.add("day");

    const isCurrentMonth =
        date.getFullYear() === displayedYear &&
        date.getMonth() === displayedMonthNumber;

    if (!isCurrentMonth) {
        element.classList.add("outside");
    }

    if (date.getDay() === 0) {
        element.classList.add("sunday");
    }

    element.setAttribute(
        "aria-label",
        `${date.getDate()} ${MONTH_NAMES[date.getMonth()]} ` +
        `${date.getFullYear()}`
    );


    /* Numer dnia */

    const number = document.createElement("span");

    number.classList.add("day-number");
    number.textContent = date.getDate();

    element.appendChild(number);

    /* Paski aktywnych terminów */

    const bars = getDayBars(date);

    if (bars.length > 0) {
        const barsContainer = document.createElement("div");

        barsContainer.classList.add("day-bars");

        for (const bar of bars) {
            barsContainer.appendChild(
                createTaskBar(bar, date)
            );
        }

        element.appendChild(barsContainer);
    }

    return element;
}

function renderCalendar() {
    const year = displayedMonth.getFullYear();
    const month = displayedMonth.getMonth();

    monthTitle.textContent =
        `${MONTH_NAMES[month]} ${year}`;

    monthSubtitle.textContent =
        "Harmonogram publikacji i terminów";

    calendarWeeks.replaceChildren();

    const startDate = getCalendarStart(year, month);

    const lastDay = new Date(year, month + 1, 0);

    const daysFromStartToLast =
        Math.round((lastDay - startDate) / 86400000);

    const numberOfWeeks =
        Math.floor(daysFromStartToLast / 7) + 1;

    for (let weekIndex = 0; weekIndex < numberOfWeeks; weekIndex++) {
        const week = document.createElement("div");

        week.classList.add("calendar-week");

        for (let dayIndex = 0; dayIndex < 7; dayIndex++) {
            const offset = weekIndex * 7 + dayIndex;

            const date = addDays(startDate, offset);

            week.appendChild(
                createDay(date, year, month)
            );
        }

        calendarWeeks.appendChild(week);
    }

    updateNavigation();
}

function updateNavigation() {
    const previousMonth = new Date(
        displayedMonth.getFullYear(),
        displayedMonth.getMonth() - 1,
        1
    );

    const nextMonth = new Date(
        displayedMonth.getFullYear(),
        displayedMonth.getMonth() + 1,
        1
    );

    previousButton.disabled =
        compareMonths(previousMonth, MIN_MONTH) < 0;

    nextButton.disabled =
        compareMonths(nextMonth, MAX_MONTH) > 0;

    previousButton.setAttribute(
        "aria-label",
        `Poprzedni miesiąc: ${MONTH_NAMES[previousMonth.getMonth()]} ` +
        `${previousMonth.getFullYear()}`
    );

    nextButton.setAttribute(
        "aria-label",
        `Następny miesiąc: ${MONTH_NAMES[nextMonth.getMonth()]} ` +
        `${nextMonth.getFullYear()}`
    );

    todayButton.textContent = "Wróć do października 2026";

    taskCount.textContent = `${SCHEDULE.length} zadań`;
}

function changeMonth(offset) {
    const target = new Date(
        displayedMonth.getFullYear(),
        displayedMonth.getMonth() + offset,
        1
    );

    if (compareMonths(target, MIN_MONTH) < 0) {
        return;
    }

    if (compareMonths(target, MAX_MONTH) > 0) {
        return;
    }

    displayedMonth = target;

    renderCalendar();
}

previousButton.addEventListener("click", () => {
    changeMonth(-1);
});

nextButton.addEventListener("click", () => {
    changeMonth(1);
});

todayButton.addEventListener("click", () => {
    displayedMonth = new Date(2026, 9, 1);

    renderCalendar();
});

document.addEventListener("keydown", event => {

    const target = event.target;

    if (
        target instanceof HTMLInputElement ||
        target instanceof HTMLTextAreaElement ||
        target instanceof HTMLSelectElement
    ) {
        return;
    }

    if (event.key === "ArrowLeft") {
        changeMonth(-1);
    }

    if (event.key === "ArrowRight") {
        changeMonth(1);
    }
});

renderCalendar();
