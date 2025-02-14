package ru.twinown.openofficetestapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test
import ru.twinown.openofficetestapp.game.LoginPage

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    private lateinit var loginPage: LoginPage

    @Before
    fun setUp() {
        loginPage = LoginPage()
    }

    /*
    * UGTC-01
    */
    @Test
    fun caseNumber1() {

        loginPage.assertInitialState()
        loginPage.assertLoginButtonDisabled()

        loginPage.addPortalText(portal = "https://testdocspaceportal.onlyoffice.com/")
        loginPage.assertValidPortalAdded()
        loginPage.assertLoginButtonDisabled()

        loginPage.addEmailText(email = "1one.test901@gmail.com")
        loginPage.assertValidEmailAdded()
        loginPage.assertLoginButtonDisabled()

        loginPage.addCorrectPasswordText(password = "Testpass123")
        loginPage.assertLoginButtonEnabled()

        loginPage.clickLoginAndWait()
        loginPage.assertDocumentsState()

        loginPage.clickNewFolderAndWait()
        loginPage.assertDocumentNewFolderState()

        loginPage.clickProfileAndWait()
        loginPage.assertProfileState()

        loginPage.clickLogout()
        loginPage.assertInitialState()
        loginPage.checkLoginButtonDisabled()

    }

    @Test
    fun caseNumber2() {

        loginPage.assertInitialState()
        loginPage.assertLoginButtonDisabled()

        loginPage.addPortalText("testdocspaceportal.onlyoffice.com/")
        loginPage.assertInvalidPortalState()
        loginPage.assertLoginButtonDisabled()


        loginPage.addPortalText(portal = "https://testdocspaceportal.onlyoffice.com/")
        loginPage.assertValidPortalAdded()
        loginPage.assertLoginButtonDisabled()

        loginPage.addEmailText("1one.test901")
        loginPage.assertInvalidEmailAdded()
        loginPage.assertLoginButtonDisabled()


        loginPage.addEmailText(email = "1one.test901@gmail.com")
        loginPage.assertValidEmailAdded()
        loginPage.assertLoginButtonDisabled()

        loginPage.addWrongPasswordText()
        loginPage.assertLoginButtonEnabled()

        loginPage.clickLoginAndWait()// ожидаем ошибку!
        loginPage.assertWrongPasswordOrEmailState()

        loginPage.addCorrectPasswordText(password = "Testpass123")
        loginPage.assertLoginButtonEnabled()
        disableWifi() // проверка отсутствия интернета

        loginPage.clickLoginAndWait()
        loginPage.assertNoInternetState()
        enableWifi()

        loginPage.clickLoginAndWait()
        loginPage.assertDocumentsState()

        loginPage.clickNewFolderAndWait()
        loginPage.assertDocumentNewFolderState()

        loginPage.clickProfileAndWait()
        loginPage.assertProfileState()

        loginPage.clickLogout()
        loginPage.assertInitialState()
        loginPage.checkLoginButtonDisabled()

    }

    private fun enableWifi() {
        Runtime.getRuntime().exec("adb shell svc wifi enable")
    }

    private fun disableWifi() {
        Runtime.getRuntime().exec("adb shell svc wifi disable")
    }
}