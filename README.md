# soa-assignments
Repository for SOA (Service-Oriented Architecture) coursework, assignments, practical exercises, and projects.


## Description
Service web RESTful de gestion des étudiants et des options (JAX-RS / RESTEasy).

## Structure
- `entities/` — Option, Etudiant, EtudiantList
- `metiers/` — OptionBusiness, EtudiantBusiness
- `ressourcesrest/` — RestOption, RestEtudiant
- `tools/` — RestActivator

## Endpoints
- `GET /rest/options`
- `POST /rest/options`
- `GET /rest/options/{code}`
- `PUT /rest/options/{code}`
- `DELETE /rest/options/{code}`
- `GET /rest/etudiants`
- `POST /rest/etudiants`
- `GET /rest/etudiants/{id}`
- `PUT /rest/etudiants/{id}`
- `DELETE /rest/etudiants/{id}`
- `GET /rest/etudiants/option?codeOption=X` (XML)

## Auteur
Mohamed Amine Rabeh
