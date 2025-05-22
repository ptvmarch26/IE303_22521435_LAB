package org.example;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AdidasShoeUI extends JFrame {
    private JPanel detailPanel;
    private Product selectedProduct;
    private final List<ProductCard> productCards = new ArrayList<>();
    private final List<Product> products;

    public AdidasShoeUI(List<Product> products) {
        this.products = products;

        setTitle("Adidas Shoe UI");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        detailPanel = new JPanel();
        detailPanel.setPreferredSize(new Dimension(250, getHeight()));
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(detailPanel, BorderLayout.WEST);

        JPanel gridPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Product product : products) {
            ProductCard card = new ProductCard(product, false, () -> {
                selectedProduct = product;
                updateDetailPanel(product);
                updateProductCardBorders();
            });
            productCards.add(card);
            gridPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        if (!products.isEmpty()) {
            selectedProduct = products.getFirst();
            SwingUtilities.invokeLater(() -> {
                updateDetailPanel(selectedProduct);
                updateProductCardBorders();
            });
        }
    }

    private void updateProductCardBorders() {
        for (ProductCard card : productCards) {
            card.setSelected(card.getProduct() == selectedProduct);
        }
    }

    // Sản phẩm chính khi hiển thị
    private void updateDetailPanel(Product product) {
        detailPanel.removeAll();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel slidePanel = new JPanel();
        slidePanel.setLayout(new BoxLayout(slidePanel, BoxLayout.Y_AXIS));
        slidePanel.setPreferredSize(new Dimension(250, detailPanel.getHeight()));
        slidePanel.setOpaque(false);
        slidePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        slidePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JLabel img = createResizedImageLabel(product.getImagePath(), 250, 200);
        img.setAlignmentX(Component.CENTER_ALIGNMENT);
        slidePanel.add(img);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        separator.setForeground(Color.GRAY);
        separator.setBackground(Color.GRAY);
        slidePanel.add(separator);
        slidePanel.add(Box.createVerticalStrut(15));

        JLabel name = new JLabel("<html><b>" + product.getName() + "</b></html>");
        name.setFont(new Font("Arial", Font.BOLD, 20));
        name.setForeground(Color.decode("#333333"));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        slidePanel.add(name);
        slidePanel.add(Box.createVerticalStrut(15));

        JLabel price = new JLabel("<html><b>" + product.getPrice() + "</b></html>");
        price.setFont(new Font("Arial", Font.BOLD, 20));
        price.setForeground(Color.decode("#333333"));
        price.setAlignmentX(Component.CENTER_ALIGNMENT);
        slidePanel.add(price);
        slidePanel.add(Box.createVerticalStrut(15));

        JLabel brand = new JLabel("<html><div style='text-align:center; color:#555555; font-size:10px;'>" + product.getBrand() + "</div></html>");
        brand.setFont(new Font("Arial", Font.PLAIN, 10));
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);
        slidePanel.add(brand);
        slidePanel.add(Box.createVerticalStrut(15));

        JLabel desc = new JLabel("<html><p style='width:150px; font-size:11px;'>" + product.getDescription() + "</p></html>");
        desc.setForeground(Color.decode("#B3B3B3"));
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        slidePanel.add(desc);

        JPanel container = new JPanel(null);
        container.setPreferredSize(new Dimension(250, detailPanel.getHeight()));
        container.setOpaque(false);

        slidePanel.setBounds(-300, 0, 250, detailPanel.getHeight());
        container.add(slidePanel);
        detailPanel.add(container);
        detailPanel.revalidate();
        detailPanel.repaint();

        Timer timer = new Timer(5, null);
        timer.addActionListener(e -> {
            Point loc = slidePanel.getLocation();
            if (loc.x < 0) {
                slidePanel.setLocation(loc.x + 10, loc.y);
                slidePanel.repaint();
            } else {
                slidePanel.setLocation(0, loc.y);
                timer.stop();
            }
        });
        timer.start();
    }

    private JLabel createResizedImageLabel(String imagePath, int width, int height) {
        URL imageUrl = getClass().getClassLoader().getResource(imagePath);
        if (imageUrl == null) {
            System.err.println("Không tìm thấy ảnh: " + imagePath);
            return new JLabel("Image not found");
        }

        ImageIcon icon = new ImageIcon(imageUrl);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaledImage));
    }
}
