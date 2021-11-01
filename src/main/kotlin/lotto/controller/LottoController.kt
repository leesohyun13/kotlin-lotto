package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumberListGenerator
import lotto.model.Lottos
import lotto.model.Price
import lotto.view.InputView
import lotto.view.OutputView

object LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun runLottoGame() {
        val price = inputPrice()
        val purchasedLottoList = generateLottos(price)
        printResult(price, purchasedLottoList)
    }

    private fun inputPrice(): Price {
        val price = Price(inputView.takePurchasedPrice())
        outputView.resultLottoCount(price.lottoCount)
        return price
    }

    private fun generateLottos(price: Price): List<Lotto> {
        val purchasedLotto = LottoNumberListGenerator(price).generateLottoList()
        outputView.printNumber(purchasedLotto)
        return purchasedLotto
    }

    private fun printResult(price: Price, purchasedLotto: List<Lotto>) {
        val winNumberList = inputView.inputLastLottoWinNumber()
        val lottos = Lottos.inputWinNumber(price, purchasedLotto, winNumberList)
        val result = lottos.compareLottoResult()
        outputView.printWinStatistic(result)
    }
}
