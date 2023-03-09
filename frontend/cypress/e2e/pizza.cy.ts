/// <reference types="cypress" />

describe("Login Page", () => {
    beforeEach(() => {
        cy.fixture("pizza").then(function (data) {
            cy.getCommand("/api/v1/pizzas", data);
            cy.deleteCommand("/api/v1/pizzas/*");
        });
        cy.visit("/");
        cy.fixture("user").then((data: { email: string, password: string }) => {
            cy.get("[data-cy=email]").type(data.email);
            cy.get("[data-cy=password]").type(data.password);
            cy.get("[data-cy=submit-login]").click();
        });
    });

    // afterEach(() => {
    //     cy.get("[data-cy=logout]").click();
    // });

    it.skip("should display login page", () => {
        cy.visit("/");
        cy.url().should("include", "/login");
        cy.get("[data-cy=auth-title]").should("contain", "Login");
    });

    it("should display logo", () => {
        cy.get("[data-cy=logo]").should("contain", "Angular CRUD");
    });

    it("should render pizzas", () => {
        cy.fixture("pizzas").then(function (data) {
            cy.get("[data-cy=row]").should("have.length", 3);
        });
    });

    it("should remove a pizza card", () => {
        const index = 1;
        cy.get("[data-cy=delete]").eq(index).click();
        cy.get("[data-cy=row]").should("have.length", 20);
    });

    it("should add a new pizza", () => {
        const name = "New pizza";
        const size = "medium";
        const price = "8.0";
        cy.get("[data-cy=create]").click();
        cy.get("[data-cy=name]").type(name);
        cy.get("[data-cy=size]").type(size);
        cy.get("[data-cy=price]").type(price);
        cy.postCommand("/api/v1/pizzas", { name, size, price });
        cy.get("[data-cy=action]").click();
        cy.fixture("pizzas").then(function (data) {
            cy.get("[data-cy=row]").should("have.length", 3);
        });
    });
});

