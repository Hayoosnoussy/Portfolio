---
name: yahiasnoussi_skill
description: Personal context about Yahia Snoussi — background, active projects, open tasks, and learning goals. Load this at the start of a session to pick up where any previous session left off, regardless of machine.
---

# Yahia Snoussi — Personal Context

## Background

- Senior Full-Stack Engineer by day, music producer by night.

## Active Projects

### Raffaella
Premium bio-cosmetics e-commerce brand (Tunisian market + international). Spring Boot + Angular, MySQL. Past MVP, now with: product variants + cost price + featured/display-order, package reviews (reviews generalized to cover products AND bundles), coupon unlimited-use threshold, admin delete/search/filter coverage (nav-section delete, search on Categories/Coupons/Packages, multi-filter on Orders), WhatsApp/Twilio removed (email-only notifications now, confirmed actually arriving), a shared admin-modal pattern used across all CRUD screens. Committed+pushed to `origin/master` 2026-08-24 (commit `cff79ac`, plus `05a608f` for `TODO.md`), on top of the full feature set first pushed 2026-08-21.
- Related skill: `/raffaella-brand` — full brand/tech context (stack, architecture, deployment gaps). **Note (2026-08-24): this is a Claude-hosted "userSettings" skill, not a local file** — it could not be edited directly this session; `project_raffaella.md` (local memory) is the current source of truth until that's resolved.
- Wishlist (products + packages) is next up — full implementation plan already written to `TODO.md` at the project root, not yet started.

### OnBoarding
Internal company project (self-initiated, not yet an official company deliverable).
- A hybrid guide for new hires, includes Scrum tooling.
- Backend migrated to hexagonal architecture (5 bounded contexts).
- Related skills: `/onboarding` — project overview skill. `/onboarding-details` — full functional analysis, kept in sync with the migration.

### Bonplan
App de bons plans locaux pour le Grand Tunis. Spring Boot + Angular 21 + Kafka + Redis + Postgres, IA via Claude API.
- Étape 1 (fondations) complète. No dedicated skill yet — context lives in project memory only.

## Related Skills

Quick index of other project-scoped skills to load for deeper context on a given project:
- `/raffaella-brand` — Raffaella e-commerce, full project skill.
- `/onboarding` — OnBoarding Hub, project skill.
- `/onboarding-details` — OnBoarding Hub, full functional analysis.

## Currently Learning

- AI / Claude AI
- Automation with n8n
- LeetCode (staying sharp on algorithms/interviews)

## Open Tasks

### Raffaella
- [x] Confirm order notification emails actually arrive — **confirmed 2026-08-24**, Yahia received a real delivery-notification email. WhatsApp/Twilio removed entirely the same session (explicit request), so that half of this task no longer applies.
- [ ] Wishlist (products + packages) — full implementation plan written to `TODO.md` at the project root 2026-08-24 (entity/DTO/repo/service/controller shape, frontend signal-cache pattern, 4 heart-icon touch points, new account page). Not started yet.
- [ ] Add password-change controller endpoint (DTO already exists)
- [ ] Decide: re-enable Stripe (clearly labeled USD) or wait for Konnect — Stripe was pulled from the checkout UI when prices switched to TND display, since Stripe can't charge in TND
- [ ] Manually test the promo banner admin flow in a browser (builds clean, untested end-to-end)
- [ ] SEO tab / "Produits associés" (related products) / AI writing assistant — explicitly deferred by Yahia when scoping the product-variants work, not started
- [ ] Package product picker (inside the package create/edit form) still capped at first 100 products, client-side filter only — no real backend search/pagination yet. (Not to be confused with the package *list* search added 2026-08-24, which is a separate, already-done thing.)
- [ ] Detailed order status history (BESOINS_ET_SUGGESTIONS.md wave 2)
- [ ] Add Docker/docker-compose, CI/CD pipeline, `.env.example`
- [ ] Migrate off `ddl-auto=update` to Flyway/Liquibase before prod — has now bitten twice on a non-empty table: `Review.status` (2026-08-22, needed NOT-NULL-with-default workaround) and `Review.product_id` (2026-08-24, relaxing NOT NULL→nullable did NOT auto-apply, needed a manual `ALTER TABLE`)

### OnBoarding
- [ ] Let a module proposer edit their own module after submission (BESOINS.md §1.2) — currently TEAM_LEAD/ADMIN only
- [ ] Migrate frontend `pages/` → `features/` (documented in ARCHITECTURE.md §3.3, not started)
- [ ] Check the `announcement/` context for the same lazy-proxy/`open-in-view=false` bug that was fixed in `community/`

### Bonplan
- [ ] Étape 2: Catalogue & recherche IA (SearchController + AnthropicService)
- [ ] Étape 3: Page établissement (galerie, menu, deals, avis)
- [ ] Étape 4: Réservation (formulaire, email Kafka, profil)
- [ ] Étape 5: Carte Mapbox
- [ ] Étape 6: Back-office admin

## Session Protocol

- Treat this file as continuity memory across sessions and machines: when starting fresh, assume the state below is the latest known state of Yahia's work.
- As work happens — status changes, tasks finished or added, new projects/ideas — update this file directly, au fur et à mesure. Don't wait until the end of a session or until asked.
- After updating, push the change straight to `github.com/YahiaSnoussi/portfolio` (branch `main`) — small incremental commits as things change, not one batched push at the end.
- Keep the local memory system (project/feedback/reference memories) refreshed and organized in step with this file, same trigger, same cadence.

## Update Log

- 2026-08-19: Initial version created (background, Raffaella, OnBoarding, learning goals).
- 2026-08-19: Added Bonplan project, linked related skills (`/onboarding`, `/onboarding-details`, `/raffaella-brand`), added per-project open-task backlog, switched to an always-update-and-push session protocol.
- 2026-08-20: Raffaella session — shipped coupons, verified-purchase product reviews, order email/WhatsApp notifications (untested with real credentials), TND currency display, add-whole-package-to-cart, admin nav-section visibility controls, category images + mega-menu hover preview, admin sidebar rework, and a notification-bell timezone bug fix. Refreshed the Raffaella open-task backlog accordingly; `/raffaella-brand` and the project's own `ARCHITECTURE.md`/`BESOINS_ET_SUGGESTIONS.md` were updated in the same session.
- 2026-08-21: Raffaella session — fixed carousel/cart images not rendering (unquoted CSS `url()` broke on filenames with spaces/apostrophes; fixed by quoting+encoding, plus sanitizing filenames at upload time going forward), fixed Google Sign-In being slow (was rebuilding a `GoogleIdTokenVerifier` + re-fetching Google's certs on every login instead of reusing one), added scroll-to-form UX on the Categories/Coupons/Carousel-slide admin edit screens, and added a 7 TND Cash-on-Delivery fee (server-computed, mirrors the coupon pattern of never trusting the frontend's number). The user separately fixed the "Erreur 401: invalid_client" Google Sign-In issue on the Google Cloud Console side, confirming it was never an app-code problem. **Also: committed and pushed the entire accumulated Raffaella feature set to `origin/master` for the first time** (commit `5bef56b`) — it had been sitting uncommitted in the working tree since 2026-07-30.
- 2026-08-24: Raffaella session — coupon `unlimitedAboveAmount` threshold (per-coupon, subtotal-based); generalized `Review` to support package/bundle reviews (dual nullable FK, same pattern as `OrderItem`) — hit a real gotcha, relaxing an existing column from NOT NULL to nullable via `ddl-auto=update` did NOT auto-apply, needed a manual `ALTER TABLE`; product↔package linking from the Product admin form; featured products + display order (checkbox + order field, `NULLS LAST` query); converted the Variants/Packages admin management from confusing below-the-fold cards to real modals, and removed an unwanted auto-open-Variants-after-Save behavior; fixed a real concurrency bug (parallel `forkJoin` variant-generation requests deadlocking in MySQL on the same Product row — reproduced directly, fixed by making them sequential); **removed WhatsApp/Twilio entirely** (explicit request) and confirmed email notifications do arrive; replaced broken/external-hotlinked placeholder images (Unsplash + two never-existed local files) with one real local SVG across 8 files; added nav-section delete (blocks if categories still reference it), search on Categories/Coupons/Packages admin lists, and a combinable multi-filter (status/customer/date/order-id) on Orders admin. Committed+pushed (`cff79ac`, then `05a608f` for a new `TODO.md`). Discovered `/raffaella-brand` is a Claude-hosted skill, not a local file — couldn't be updated directly this session. Wishlist (products + packages) scoped in full but deferred to next session — see `TODO.md`.
- 2026-08-22: Raffaella — two large staged builds, both planned via subagent research + `EnterPlanMode` first, each stage curl/tsc-verified against the live dev instance. (1) **Product variants + cost price**: variants optional per product, `Product.price`/`stockQuantity` become a server-maintained aggregate cache once a product has variants (so almost nothing else in the app needed to change); fixed a real cart bug where two variants of the same product used to silently merge into one line. (2) **7-feature admin/storefront batch**: a shared `admin-modal` component now used by every admin CRUD screen (Categories/Coupons/Carousel/Products/Packages); Packages got real edit capability + image upload + a product-picker search + inline "new product" creation (backend bundles got real DTOs, replacing raw-entity request/response bodies); atomic homepage-reset endpoint; a 24-governorate Tunisian dropdown in checkout/profile/admin; **dynamic DB-driven navbar mega-menu sections** replacing four separate hardcoded copies of the section list (caught and fixed a seeder bug that would never have backfilled existing rows with new metadata — verified the fix self-heals on restart); and **review moderation** (3-state PENDING/APPROVED/REJECTED, not a boolean, so rejected reviews stay auditable instead of disappearing) with a new Admin Reviews page and a second independent notification bell. Caught a MySQL strict-mode `ALTER TABLE` failure risk before it shipped (checked row count on a non-empty table before adding a would-be `NOT NULL` column, made it nullable instead, manually backfilled the one existing row). **Found and excluded a real secret from the commit**: a live Gmail App Password had been filled into `application.properties` locally — committed everything else, left that one file's diff out, flagged it clearly. Committed+pushed as commit `976354a`. `/raffaella-brand` fully refreshed to match.
