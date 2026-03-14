# Spring Boot Course Notes

Quick notes so future me can revise fast.

## 1) Spring Boot in one shot
- Spring Boot = fast way to build Java backend apps.
- It auto-configures common setup so I write less boilerplate.
- It starts server + app context and handles HTTP requests.

## 2) My project right now
- Beginner backend project.
- Has `/` test route and content-related classes.
- No DB yet.
- Data is in-memory list, so data resets on restart.

## 3) Request flow
- Browser/client hits a URL.
- Spring matches that URL to a controller method.
- Controller handles the request.
- Controller can call Service for business logic.
- Service can call Repository for data access.
- Model is the data object passed between layers.

## 4) MVC clarity
- `model` = data classes (`Content`, enums, etc).
- `view` = UI templates/pages (not used right now).
- Current style is REST backend: controller + model + repository (+ service later).

## 5) File map
- `SpringCourseApplication` = starts the whole app.
- `HomeController` = test route for `/`.
- `ContentController` = handles content URLs.
- `ContentCollectionRepository` = stores and finds content data.
- `Content` = one content object.
- `Type` and `Status` = fixed allowed values.

## 6) Layer cheat sheet
- Controller = request/response layer.
- Service = business rules/workflow layer.
- Repository = read/write data layer.
- Model = data shape layer.

## 7) Model / Controller / Repo / Service (final understanding)
- Model = class/record/enums for data shape + allowed values.
- Controller = methods mapped to URLs (`@GetMapping`, etc).
- Repository = data operations (list now, DB later).
- Service = logic layer between controller and repository.

## 8) Dependency Injection (DI)
- Spring creates objects for me.
- Spring gives needed object to another class automatically.
- I avoid writing `new` everywhere.
- Short: Spring wires classes.

## 9) Application context
- Application context = Spring's object box.
- Spring-managed classes live here (`@RestController`, `@Repository`, etc).
- DI pulls objects from this box.

## 10) Constructor injection
- Field = box where dependency is stored.
- Constructor = tool that fills the box.
- Need both: one stores, one fills.

## 11) `private final repository`
- `private` = only this class can access directly.
- `final` = assign once, do not replace.
- Safer and cleaner design.

## 12) `@Autowired`
- Means: Spring inject this dependency.
- Same goal as constructor injection.
- With single constructor, explicit `@Autowired` is usually not needed.

## 13) Annotation quick refs
- `@SpringBootApplication` = main app class.
- `@RestController` = HTTP endpoint class.
- `@RequestMapping` = base path for controller.
- `@GetMapping` = GET endpoint mapping.
- `@Repository` = data access component.

## 14) Java quick refs
- `private` = class-only access.
- `public` = accessible from other classes.
- `final` = cannot reassign.
- Constructor = runs when object is created.
- `record` = short data class.
- `enum` = fixed value set.

## 15) Current endpoints
- `/` -> returns `Spring Course App is Running!`
- `/api/content` and `/content` -> returns all content.
- `/api/content/{id}` and `/content/{id}` -> returns one content by id.
- `POST /api/content` and `POST /content` -> create content from request body.
- `PUT /api/content/{id}` and `PUT /content/{id}` -> update content by id.
- `DELETE /api/content/{id}` and `DELETE /content/{id}` -> delete content by id.

## 16) Current architecture
- Right now it is mostly `Controller -> Repository -> Model`.
- Service layer is still missing.
- Target flow: `Controller -> Service -> Repository -> Model`.
- Controller should mostly: define route, take input, call service, return response/status.

## 17) Next learning steps
- Add methods in `ContentController`
- Add `ContentService`
- Return JSON from endpoints
- Learn `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- Connect to a real database later

## 18) Daily commands (basic)
- Compile only: `mvn clean compile`
- Build jar: `mvn clean package`
- Run app (Spring): `mvn spring-boot:run`
- Run tests: `mvn test`
- Run built jar: `java -jar target/*.jar`
- Kill port to restart app : lsof -ti tcp:8080 | xargs kill -9
- If `mvn` is not installed, use project wrapper with `./mvnw ...`

## 19) One-line recap
- Spring Boot starts app, controller handles URLs, repository handles data, model holds data shape, DI wires objects, service layer comes next.

## 20) All annotations + inbuilt functions used (one place)
- `@SpringBootApplication` -> marks main app class.
- `@RestController` -> class handles HTTP API requests.
- `@RequestMapping` -> base route for a controller.
- `@CrossOrigin` -> allows frontend from different origin/port to call this API.
- `@GetMapping` -> GET route mapping.
- `@PostMapping` -> POST route mapping.
- `@PutMapping` -> PUT route mapping.
- `@DeleteMapping` -> DELETE route mapping.
- `@ResponseStatus` -> send custom HTTP status (like `201 Created`, `204 No Content`).
- `@PathVariable` -> take value from URL path (like Express `req.params`).
- `@RequestBody` -> take JSON body and map it to Java object.
- `@Repository` -> marks data access class as Spring-managed bean.
- `@PostConstruct` -> run method once after bean is created.

## 21) Inbuilt functions / classes used
- `Optional<T>` -> value may or may not exist.
- `orElseThrow(...)` -> throw error if Optional is empty.
- `orElseThrow` takes an exception builder (`() -> new RuntimeException(...)`), not plain text.
- `ResponseStatusException(HttpStatus.NOT_FOUND, "...")` -> return proper 404 with message.
- `stream().filter(...).findFirst()` -> find first matching item from list.
- `removeIf(...)` -> remove list items matching a condition.
- `LocalDateTime.now()` -> current date-time value.

## 22) `@PostConstruct` vs `@PostMapping`
- `@PostConstruct` -> runs once when app starts (startup/init hook).
- `@PostMapping` -> runs when client sends POST API request.

## 23) Frontend error handling mini note
- API should return right status code (`404` for not found).
- Frontend checks status code, then shows custom text like: `No content found with that id`.

## 24) Repo structure now (backend + frontend)
- `backend/` = Spring Boot API project.
- `frontend/` = React + Vite frontend project.
- Frontend fetches: `/api/content` (Vite proxy sends to Spring `localhost:8080`).

## 25) Frontend quick run
- From repo root: `cd frontend && npm install && npm run dev`
- From repo root: `cd backend && ./mvnw spring-boot:run`
- Open frontend URL and check browser console for: `content json:`

## 26) CORS mini note
- If frontend and backend run on different ports, browser blocks calls unless CORS is allowed.
- `@CrossOrigin` on controller lets frontend call those API routes.
