package org.example;

import org.example.Product;
import org.example.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ProductCard extends RoundedPanel {
    private final Product product;
    private boolean isSelected = false;

    public ProductCard(Product product, boolean isSelected, Runnable onClick) {
        super(25);
        this.product = product;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(220, 220, 220));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setOpaque(false);

        setMaximumSize(new Dimension(170, 300));
        setPreferredSize(new Dimension(170, 300));

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onClick.run();
            }
        });

        createProductCard();
    }

    // Tạo layout cho sản phẩm
    private void createProductCard() {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel name = new JLabel("<html><b>" + truncate(product.getName(), 14) + "</b></html>");
        name.setFont(new Font("Arial", Font.BOLD, 22));
        name.setForeground(Color.decode("#333333"));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel desc = new JLabel("<html><p style='width:150px; font-size:10px;'>" + truncate(product.getDescription(), 30) + "</p></html>");
        desc.setForeground(Color.decode("#B3B3B3"));
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel imgLabel = createResizedImageLabel(product.getImagePath(), 200, 160);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel brand = new JLabel("<html><div style='text-align:center; color:#555555; font-size:10px;'>" + product.getBrand() + "</div></html>");
        brand.setFont(new Font("Arial", Font.PLAIN, 13));
        brand.setForeground(Color.GRAY);
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel price = new JLabel("<html><b>" + product.getPrice() + "</b></html>");
        price.setFont(new Font("Arial", Font.BOLD, 20));
        price.setForeground(Color.decode("#333333"));
        price.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel infoRow = new JPanel(new BorderLayout());
        infoRow.setOpaque(false);
        infoRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoRow.add(brand, BorderLayout.WEST);
        infoRow.add(price, BorderLayout.EAST);

        add(name);
        add(desc);
        add(imgLabel);
        add(Box.createVerticalStrut(5));
        add(infoRow);
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

    private String truncate(String text, int maxLength) {
        return text.length() <= maxLength ? text : text.substring(0, maxLength - 3) + "...";
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isSelected) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0, 120, 215));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25); // 25 là corner radius
            g2.dispose();
        }
    }

    public Product getProduct() {
        return product;
    }
}
